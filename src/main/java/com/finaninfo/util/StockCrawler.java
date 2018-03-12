package com.finaninfo.util;

import com.finaninfo.stock.dto.StockDividendRate;
import com.finaninfo.stock.dto.StockDto;
import com.finaninfo.stock.dto.StockPriceDto;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import static javafx.beans.binding.Bindings.select;

public class StockCrawler {
    private static String regexNumber ="^[-+]?([0]{1}(\\.[0-9]+)?|[1-9]{1}\\d*(\\.[0-9]+)?)"; //+0.05,-0.05,+11.05,-11.05

    public static String formatStockCode(String code){
        if (code.startsWith("60")){
            return String.format("sh%s",code);
        }
        else if (code.startsWith("5")){
            return String.format("sh%s",code);
        }
        else if (code.startsWith("1")){
            return String.format("sz%s",code);
        }
        else if (code.startsWith("00")){
            return String.format("sz%s",code);
        }


        return null;
    }

    public static Double mathFormat(String value) {
        String v = value.replaceAll("\\%", "").replace("亿", "");
        if (v.matches(regexNumber)) {
            return Double.valueOf(v);
        }
        return -1D;
    }

    public static List<StockPriceDto> getStockPrice(String[] stockCode)throws Exception{
        StringBuffer queryString = new StringBuffer();
        for (String code:stockCode) {
            String result = formatStockCode(code);
            if (result != null)
                queryString.append(result).append(",");
        }

        String queryCode = queryString.toString().replaceAll("\\,$","");
        String query = String.format("http://hq.sinajs.cn/list=%s", queryCode);

        String content = Jsoup.connect(query).ignoreContentType(true).get().text();
        String[] line = content.split(";");
        List<StockPriceDto> list = new ArrayList<>();
        for (String s: line){
            String row = s.trim().replaceAll("^var\\D+|\"","").replace("=",",");
            String data[] = row.split(",");
            double currentPrice = Double.parseDouble(data[4]);
            double yesterdayClose = Double.parseDouble(data[3]);
            double increaseAmount = (currentPrice-yesterdayClose)/yesterdayClose*100;

            NumberFormat nf = NumberFormat.getNumberInstance();

            nf.setMaximumFractionDigits(2);
            nf.setRoundingMode(RoundingMode.UP);

            list.add(new StockPriceDto(data[0],data[1],currentPrice,yesterdayClose,Double.valueOf(nf.format(increaseAmount))));
        }
        return list;
    }

    public static StockDto getStockInfo(String code)throws Exception{
        String url = String.format("http://basic.10jqka.com.cn/16/%s",code);
        Document document = Jsoup.connect(url).get();
        Elements table = document.getElementsByTag("table");

        Elements firstTableTds = table.get(0).select("tr").get(0).select("td");

        String mainBusiness = firstTableTds.get(0).text().replaceAll(".*\\：|\\s*", "");
        String industry = firstTableTds.get(1).text().replaceAll(".*\\：|\\s*", "");

        Elements secondTableTds = table.get(1).select("td");
        String PeDynamic = secondTableTds.get(0).text().replaceAll(".*\\：|\\s*", "");
        String PeStatic = secondTableTds.get(4).text().replaceAll(".*\\：|\\s*", "");

        String Pb = secondTableTds.get(8).text().replaceAll(".*\\：|\\s*", "");
        String totalValue = secondTableTds.get(11).text().replaceAll("\\D+", "");

        String roe = "-1";

        if (secondTableTds.size() >14)
             roe= secondTableTds.get(14).select("span").get(1).text();

        StockDto stockDto = new StockDto();
        stockDto.setSMainBusiness(mainBusiness);
        stockDto.setSIndustry(industry);
        stockDto.setSPeDynamic(BigDecimal.valueOf(mathFormat(PeDynamic)));
        stockDto.setSPeStatic(BigDecimal.valueOf(mathFormat(PeStatic)));
        stockDto.setSPb(BigDecimal.valueOf(mathFormat(Pb)));
        stockDto.setSTotalValue(BigDecimal.valueOf(mathFormat(totalValue)));
        stockDto.setSRoe(BigDecimal.valueOf(mathFormat(roe)));
        return stockDto;
    }


    public static List<StockDividendRate> getStockDivdendRate(String code)throws Exception{
        String url = String.format("http://basic.10jqka.com.cn/16/%s/bonus.html",code);
        Document document = Jsoup.connect(url).get();
        Element table = document.getElementById("bonus_table");
        if (table != null){
            Elements rows = table.getElementsByTag("tr");
            List<StockDividendRate> list = new ArrayList<>();

            for (int i = 0; i < rows.size() ; i++) {
                String[] data = rows.get(i).select("td").text().split(" ");

                double value = -1;

                if (data[9] != null) {
                    String temp = data[9].replace("%", "");
                    if (temp.matches(regexNumber))
                        value = Double.parseDouble(temp);
                }
                list.add(new StockDividendRate(data[0],data[6],data[4],value));
            }
        return list;
        }
        return null;
    }


    public static void guzhi(String code,String date)throws Exception{
        String url = String.format(
                "http://www.csindex.com.cn/sseportal/csiportal/syl/indexsyl.do?indexCode=%s%s",
                code, date == null ?"":"&date="+date);
        System.out.println(url);
        Elements content = Jsoup.connect(url)
                .ignoreContentType(true)
                .userAgent("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/60.0.3112.78 Safari/537.36")
                .referrer("http://www.csindex.com.cn/sseportal/csiportal/hy_syl/syl.jsp")
                .timeout(3000)
                .get()
                .getElementsByTag("tr");
        if(content.size() == 2){
            String row = content.get(1).text();
            String data[] = row.split("\\s");
            System.out.println(row);
        }else
            System.out.println("没数据");

    }

}
