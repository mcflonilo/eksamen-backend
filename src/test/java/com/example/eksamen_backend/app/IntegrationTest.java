package com.example.eksamen_backend.app;

import com.example.eksamen_backend.model.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
@SpringBootTest
@AutoConfigureMockMvc
public class IntegrationTest {
    @Autowired
    protected MockMvc mockMvc;
    @Test
    void shouldGetCustomer() throws Exception {
        mockMvc.perform(get("/api/customer/page/0"))
                .andExpect(status().isOk())
                .andDo(mvcResult -> {
            System.out.println(mvcResult.getResponse().getContentAsString());
        });
    }
    //https://stackoverflow.com/questions/20504399/testing-springs-requestbody-using-spring-mockmvc
    public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType()
            , MediaType.APPLICATION_JSON.getSubtype()
            , StandardCharsets.UTF_8);
    @Test
    public void testInsertObject() throws Exception {
            String url = "/api/customer";
            Customer customer = new Customer();
            customer.setName("mockTestCustomer");
            customer.setAddress(new Address("mockTestAddress"));

            Part part = new Part("mockTestPart");
            Sub_assembly sub_assembly = new Sub_assembly("mockTestSubAssembly");
            sub_assembly.setPart(part);
            Machine machine = new Machine("mockTestMachine");
            machine.setSub_assemblies(sub_assembly);

            Order order = new Order();
            order.setMachine(machine);

            customer.getOrders().add(order);
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
            ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
            String requestJson=ow.writeValueAsString(customer);
            System.out.println(requestJson);
            mockMvc.perform(post(url).contentType(APPLICATION_JSON_UTF8)
                            .content(requestJson))
                    .andExpect(status().isOk());
    }
}
