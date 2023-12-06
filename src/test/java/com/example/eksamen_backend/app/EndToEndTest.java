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

import java.nio.charset.StandardCharsets;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
@SpringBootTest
@AutoConfigureMockMvc
public class EndToEndTest {
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
        Sub_assembly sub_assembly2 = new Sub_assembly("mockTestSubAssembly2");
        sub_assembly.setPart(part);
        Machine machine = new Machine("mockTestMachine");
        machine.setSub_assemblies(sub_assembly);
        machine.setSub_assemblies(sub_assembly2);

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
    @Test
    public void testInsertObjectSubAssemblyNonArray() throws Exception {
        String url = "/api/customer";
        String requestJson="{\n" +
                "  \"customer_id\" : 0,\n" +
                "  \"name\" : \"mockTestCustomer\",\n" +
                "  \"orders\" : [ {\n" +
                "    \"orderId\" : 0,\n" +
                "    \"machines\" : [ {\n" +
                "      \"machine_id\" : 0,\n" +
                "      \"machine_name\" : \"mockTestMachine\",\n" +
                "      \"sub_assemblies\" :  {\n" +
                "        \"sub_assembly_id\" : 0,\n" +
                "        \"sub_assembly_name\" : \"mockTestSubAssembly\",\n" +
                "        \"parts\" : [ {\n" +
                "          \"part_id\" : 0,\n" +
                "          \"part_name\" : \"mockTestPart\"\n" +
                "        },\n" +
                "        {\n" +
                "          \"part_id\" : 0,\n" +
                "          \"part_name\" : \"mockTestPart2\"\n" +
                "        } ]\n" +
                "      } \n" +
                "    } ]\n" +
                "  } ],\n" +
                "  \"addresses\" : [ {\n" +
                "    \"address_id\" : 0,\n" +
                "    \"address\" : \"mockTestAddress\"\n" +
                "  } ]\n" +
                "}";
        System.out.println(requestJson);
        mockMvc.perform(post(url).contentType(APPLICATION_JSON_UTF8)
                        .content(requestJson))
                .andExpect(status().isOk());
    }
}
