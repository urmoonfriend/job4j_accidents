package kz.job4j.accidents.controller;

import kz.job4j.accidents.EnvConfigTest;
import kz.job4j.accidents.model.Accident;
import kz.job4j.accidents.model.AccidentType;
import kz.job4j.accidents.model.Rule;
import kz.job4j.accidents.service.AccidentService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class AccidentControllerTest extends EnvConfigTest {

    @Autowired
    private MockMvc mockMvc;
    @Mock
    private AccidentService service;

    @Test
    @WithMockUser
    public void whenGetAllThenOk() throws Exception {
        when(service.getAll()).thenReturn(List.of());
        this.mockMvc.perform(get("/accidents"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("/accidents/list"));
    }

    @Test
    @WithMockUser
    public void whenCreateThenOk() throws Exception {
        when(service.getTypes()).thenReturn(List.of(new AccidentType().setId(1).setName("type")));
        when(service.getRules()).thenReturn(List.of(new Rule().setId(1).setName("rule")));
        this.mockMvc.perform(get("/accidents/create"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("/accidents/create"));
    }

    @Test
    @WithMockUser
    //@Transactional
    public void whenGetByIdThenOk() throws Exception {
        AccidentType type = new AccidentType().setId(1).setName("type");
        Rule rule = new Rule().setId(1).setName("rule");
        Accident accident = new Accident()
                .setId(1)
                .setName("accident")
                .setText("accident")
                .setRules(Set.of(rule))
                .setAddress("address")
                .setType(type);
        when(service.getTypes()).thenReturn(List.of(type));
        when(service.getRules()).thenReturn(List.of(rule));
        when(service.getById(any(Integer.class))).thenReturn(Optional.of(accident));
        this.mockMvc.perform(get("/accidents/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("/accidents/edit"));
    }

    @Test
    @WithMockUser
    @Transactional
    public void whenGetByIdThenNotFound() throws Exception {
        when(service.getById(any(Integer.class))).thenReturn(Optional.empty());
        this.mockMvc.perform(get("/accidents/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("/accidents/edit"));
    }
}