package com.example.chefgo;

import org.json.JSONObject;
import org.junit.Before;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class OrderTest {

    @Mock
    private JSONObject review, chef, customer;

    @InjectMocks
    private Order order;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }




}
