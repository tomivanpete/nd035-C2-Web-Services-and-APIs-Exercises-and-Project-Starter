package com.udacity.pricing.api;

import com.udacity.pricing.service.PricingService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import java.util.Random;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PricingController.class)
public class PricingControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PricingService pricingService;

    /**
     * Tests that the PricingController returns
     * a '200 - OK' response for vehicleId in the range 1 - 20.
     * @throws Exception if the mock request fails
     */
    @Test
    public void testGetPrice() throws Exception {
        long price = (long) new Random().nextInt(20) + 1;

        mockMvc.perform(get("/services/price?vehicleId=" + price))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    /**
     * Tests that the PricingController returns
     * a '404 - Not Found' response for an invalid vehicleId.
     * @throws Exception if the mock request fails
     */
    @Test
    public void testInvalidVehicleId() throws Exception {
        long invalidVehicleId = 99L;

        mockMvc.perform(get("/services/price?vehicleId=" + invalidVehicleId))
                .andExpect(status().isNotFound());
    }
}
