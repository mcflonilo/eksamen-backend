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
    @Test
    void shouldGetCustomerById() throws Exception {
        mockMvc.perform(get("/api/customer/1"))
                .andExpect(status().isOk())
                .andDo(mvcResult -> {
            System.out.println(mvcResult.getResponse().getContentAsString());
        });
    }
    @Test
    void shouldPostCustomer() throws Exception {
        mockMvc.perform(post("/api/customer")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"testCustomer\"}"))
                .andExpect(status().isOk())
                .andDo(mvcResult -> {
            System.out.println(mvcResult.getResponse().getContentAsString());
        });
    }



    @Test
    void shouldGetAddress() throws Exception {
        mockMvc.perform(get("/api/address/page/0"))
                .andExpect(status().isOk())
                .andDo(mvcResult -> {
            System.out.println(mvcResult.getResponse().getContentAsString());
        });
    }
    @Test
    void shouldGetAddressById() throws Exception {
        mockMvc.perform(get("/api/address/1"))
                .andExpect(status().isOk())
                .andDo(mvcResult -> {
            System.out.println(mvcResult.getResponse().getContentAsString());
        });
    }
    @Test
    void shouldPostAddress() throws Exception {
        mockMvc.perform(post("/api/address")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"address\":\"testAddress\"}"))
                .andExpect(status().isOk())
                .andDo(mvcResult -> {
            System.out.println(mvcResult.getResponse().getContentAsString());
        });
    }
    @Test
    void shouldGetMachine() throws Exception {
        mockMvc.perform(get("/api/machine/page/0"))
                .andExpect(status().isOk())
                .andDo(mvcResult -> {
            System.out.println(mvcResult.getResponse().getContentAsString());
        });
    }
    @Test
    void shouldGetMachineById() throws Exception {
        mockMvc.perform(get("/api/machine/1"))
                .andExpect(status().isOk())
                .andDo(mvcResult -> {
            System.out.println(mvcResult.getResponse().getContentAsString());
        });
    }
    @Test
    void shouldPostMachine() throws Exception {
        mockMvc.perform(post("/api/machine")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"machine_name\":\"testMachine\"}"))
                .andExpect(status().isOk())
                .andDo(mvcResult -> {
            System.out.println(mvcResult.getResponse().getContentAsString());
        });
    }
    @Test
    void shouldGetOrder() throws Exception {
        mockMvc.perform(get("/api/order/page/0"))
                .andExpect(status().isOk())
                .andDo(mvcResult -> {
            System.out.println(mvcResult.getResponse().getContentAsString());
        });
    }
    @Test
    void shouldGetOrderById() throws Exception {
        mockMvc.perform(get("/api/order/1"))
                .andExpect(status().isOk())
                .andDo(mvcResult -> {
            System.out.println(mvcResult.getResponse().getContentAsString());
        });
    }
    @Test
    void shouldPostOrder() throws Exception {
        mockMvc.perform(post("/api/order")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"orderId\":\"1\"}"))
                .andExpect(status().isOk())
                .andDo(mvcResult -> {
            System.out.println(mvcResult.getResponse().getContentAsString());
        });
    }
    @Test
    void shouldGetPart() throws Exception {
        mockMvc.perform(get("/api/part/page/0"))
                .andExpect(status().isOk())
                .andDo(mvcResult -> {
            System.out.println(mvcResult.getResponse().getContentAsString());
        });
    }
    @Test
    void shouldGetPartById() throws Exception {
        mockMvc.perform(get("/api/part/1"))
                .andExpect(status().isOk())
                .andDo(mvcResult -> {
            System.out.println(mvcResult.getResponse().getContentAsString());
        });
    }
    @Test
    void shouldPostPart() throws Exception {
        mockMvc.perform(post("/api/part")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"part_name\":\"testPart\"}"))
                .andExpect(status().isOk())
                .andDo(mvcResult -> {
            System.out.println(mvcResult.getResponse().getContentAsString());
        });

    }
    @Test
    void shouldGetSubAssembly() throws Exception {
        mockMvc.perform(get("/api/subAssembly/page/0"))
                .andExpect(status().isOk())
                .andDo(mvcResult -> {
            System.out.println(mvcResult.getResponse().getContentAsString());
        });
    }
    @Test
    void shouldGetSubAssemblyById() throws Exception {
        mockMvc.perform(get("/api/subAssembly/1"))
                .andExpect(status().isOk())
                .andDo(mvcResult -> {
            System.out.println(mvcResult.getResponse().getContentAsString());
        });

    }
    @Test
    void shouldPostSubAssembly() throws Exception {
        mockMvc.perform(post("/api/subAssembly")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"sub_assembly_name\":\"testSubAssembly\"}"))
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
    public void testInsertComplexObject() throws Exception {
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
        mockMvc.perform(post(url).contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(status().isOk());
    }
    @Test
    public void testInsertComplexObjectSubAssemblyNonArray() throws Exception {
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
