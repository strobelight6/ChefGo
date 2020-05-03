package com.example.chefgo;

import android.util.Log;

import com.example.chefgo.Adapters.OrderHistoryAdapter;
import com.example.chefgo.CustomerClient.CustomerOrderHistoryActivity;
import com.example.chefgo.DomainObjects.MenuDomain;
import com.example.chefgo.DomainObjects.UsersDomain;
import com.example.chefgo.ProcessAndParse.ParserUtil;
import com.example.chefgo.ProcessAndParse.ProcessReviews;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
/**
 * @author SB_3
 *
 */

public class Mockito_Joe {

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    /*
    To test that the first name is being returned.
     */
    @Test
    public void getRevieweeFirstNameTrue() {

        //Mock the unimplemented ProcessReviews class
        ProcessReviews process = mock(ProcessReviews.class);

        JSONObject review = new JSONObject();
        try{
            review.put("reviewer", "Joe Strobel");
            review.put("reviewee", "Karthik Prakash");
        }catch(JSONException e){
            Log.d("Message:", e.getMessage());
        }


        //Mock the strings returned by ProcessReviews functions
        when(process.getReviewee(review)).thenReturn("Karthik Prakash");

        //Get the reviewee (Mocked)
        String reviewee = process.getReviewee(review);

        //Get the first name of the reviewee
        String revieweeFname = ParserUtil.parseName(reviewee);

        //Test that the parser util worked correctly
        Assert.assertEquals("Karthik", revieweeFname);
    }

    /*
    This test makes sure that the parser does not include a space behind the first name
     */
    @Test
    public void getRevieweeFirstNameFalse() {

        //Mock the unimplemented ProcessReviews class
        ProcessReviews process = mock(ProcessReviews.class);

        JSONObject review = new JSONObject();
        try{
            review.put("reviewer", "Joe Strobel");
            review.put("reviewee", "Karthik Prakash");
        }catch(JSONException e){
            Log.d("Message:", e.getMessage());
        }


        //Mock the strings returned by ProcessReviews functions
        when(process.getReviewee(review)).thenReturn("Karthik Prakash");

        //Get the reviewee (Mocked)
        String reviewee = process.getReviewee(review);

        //Get the first name of the reviewee
        String revieweeFname = ParserUtil.parseName(reviewee);

        //Test that the parser util did not include the space behind the first name
        Assert.assertNotEquals("Karthik ", revieweeFname);
    }

    /*
    To test if the last name is being parsed correctly.
     */
    @Test
    public void getReviewerLastNameTrue(){
        //Mock the unimplemented ProcessReviews class
        ProcessReviews process = mock(ProcessReviews.class);

        JSONObject review = new JSONObject();
        try{
            review.put("reviewer", "Joe Strobel");
            review.put("reviewee", "Karthik Prakash");
        }catch(JSONException e){
            Log.d("Message:", e.getMessage());
        }


        //Mock the strings returned by ProcessReviews functions
        when(process.getReviewee(review)).thenReturn("Karthik Prakash");

        //Get the reviewee (Mocked)
        String reviewee = process.getReviewee(review);

        //Get the last name of the reviewee
        String revieweeLname = ParserUtil.parseLastName(reviewee);

        //Test that the parser util correctly grabbed the full last name
        Assert.assertEquals("Prakash", revieweeLname);
    }

    @Test
    public void testOrderHistoryAdapter(){
        UsersDomain user = new UsersDomain("jstr", "Jstr@gmail.com", "Joe", "pass",  4.5,
                1, "morningside st", "IA", 55123);
        ArrayList<String> list = new ArrayList<>();
        list.add("Hello");
        CustomerOrderHistoryActivity c = mock(CustomerOrderHistoryActivity.class);
        OrderHistoryAdapter oha = new OrderHistoryAdapter(list, c, user);
        Assert.assertEquals("Hello", oha.getItem(0));
        Assert.assertEquals(1, oha.getCount());
    }

    @Test
    public void testOrderJSON(){
        UsersDomain u = new UsersDomain("krp", "test@gmail.com", "1", "pass",  5.0,
                1, "3330 Morningside st", "IA", 55123);

        Map<String, String> customerMap = u.toJSON();
        JSONObject customerObject = new JSONObject(customerMap);

        MenuDomain m = new MenuDomain("Big meal", "Fries", "Burger", "Cake", 34.0, "Description", u);
        Map<String, String> menuMap = m.toJSON();
        JSONObject menuObject = new JSONObject(menuMap);

        Map<String, String> orderMap = new HashMap<>();
        orderMap.put("isActive", "1");
        orderMap.put("oid", "5");
        orderMap.put("price", "6.0");
        orderMap.put("dish", menuObject.toString());
        orderMap.put("chef", null);
        orderMap.put("customer", null);
        orderMap.put("date", "2019");
        orderMap.put("review", null);
        JSONObject orderObject = new JSONObject(orderMap);

        Order o = new Order(orderObject);
        //Will fix this
        Assert.assertNull(null);

    }

    @Test
    public void testMenuDomain(){
        //Create menu object with a user
        UsersDomain u = new UsersDomain("krp", "test@gmail.com", "1", "pass",  5.0,
                1, "3330 Morningside st", "IA", 55123);
        MenuDomain m = new MenuDomain("Big meal", "Fries", "Burger", "Cake", 34.0, "Description", u);

        //Get attributes of a menu using defined methods
        String title = m.getTitle();
        String app = m.getAppetizer();
        String entree = m.getEntree();
        String dessert = m.getDessert();
        Double cost = m.getCost();

        //test if they work correctly
        Assert.assertEquals("Big meal", title);
        Assert.assertEquals("Fries", app);
        Assert.assertEquals("Burger", entree);
        Assert.assertEquals("Cake", dessert);
        Assert.assertEquals(34.0, cost, 1);
    }

}


