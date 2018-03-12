package com.finaninfo.contrlloer;

import com.finaninfo.commands.NewsCommand;
import com.finaninfo.service.ImageService;
import com.finaninfo.service.NewsService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ImageControllerTest {

    @Mock
    ImageService imageService;

    @Mock
    NewsService newsService;

    ImageController controller;

    MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        controller = new ImageController();
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void newsUploadForm() throws Exception {
        //given
        NewsCommand command = new NewsCommand();
        command.setId(1L);

        when(newsService.findCommandById(anyLong())).thenReturn(command);

        //when
        mockMvc.perform(get("/admin/news-edit/1"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("news"));

        verify(newsService, times(1)).findCommandById(anyLong());
    }

    @Test
    public void handleImagePost() {
    }

    @Test
    public void renderImageFromDB() {
    }
}