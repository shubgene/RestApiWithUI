package com.asellion.assignment.assignment;


     
    import org.junit.Test;
    import org.junit.runner.RunWith;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
    import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
    import org.springframework.test.web.servlet.MockMvc;

	import com.asellion.assignment.assignment.Entity.ProductEntity;
	import com.asellion.assignment.assignment.Service.ProductDaoService;
import com.asellion.assignment.assignment.Service.SecurityConfig;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

    import static org.mockito.BDDMockito.given;
    import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
    import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

    @RunWith(SpringRunner.class)
    @WebMvcTest
    public class AssignmentApplicationTests {
        @Autowired
        private MockMvc mockMvc;

        @MockBean
        private ProductDaoService productService;

        @Test
        @WithMockUser(username = "product", roles={"ADMIN"})
        public void findAll() throws Exception {
            // given
            ProductEntity pro = new ProductEntity();
            pro.setId(1);
            pro.setName("TV");
            pro.setCurrentPrice(560);

            List<ProductEntity> products = Arrays.asList(pro);
            given(productService.findAll()).willReturn(products);

            // when + then
            this.mockMvc.perform(get("/api/products"))
                    .andExpect(status().isOk())
                    .andExpect(content().json("[{'id': 1,'name': 'TV';'currentPrice': 560}]"));
        }
        
        @Test
        @WithMockUser(username = "product", roles={"ADMIN"})
        public void create() throws Exception {
            // given
            ProductEntity pro = new ProductEntity();
            pro.setName("Headphone");
            pro.setCurrentPrice(570);
            pro.setId(1);
            pro.setLastUpdate(new Date());
            String json=asJsonString(pro); 
            // when + then
            
            this.mockMvc.perform(post("/api/products").content(json).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isCreated());
            
         
            }
        
        public static String asJsonString(final Object obj) {
            try {
                return new ObjectMapper().writeValueAsString(obj);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

    }

    