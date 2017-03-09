package io.github.timladenov.interactiveapplication;

/**
 * Add your package below. Package name can be found in the project's AndroidManifest.xml file.
 * This is the package name our example uses:
 *
 * package com.example.android.justjava;
 */

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {
    /*Button orderButton = (Button) findViewById(R.id.Button1);
    orderButton.setOnClickListener(new View.OnClickListener());*/
    public int numberOfCoffees = 0;
    public static final int coffeePrice=5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        display(numberOfCoffees);
    }

    //View orderView = findViewById(R.id.Button1);

    public void onClick(View orderView) {

        plusOrder(orderView);
    }
    public void onClickMinus(View orderView ) {
        minusOrder(orderView);
    }
    public void onClickOrder(View orderview) {
        displayOrder(orderview);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void plusOrder(View view) {
        numberOfCoffees++;
        display(numberOfCoffees);
        //displayPrice(numberOfCoffees*coffeePrice);
    }

    public void minusOrder(View view) {
        if(numberOfCoffees > 0) {
            numberOfCoffees--;
            display(numberOfCoffees);
            //displayPrice(numberOfCoffees*coffeePrice);
        }
    }

    public void displayOrder(View view) {
        displayPrice(numberOfCoffees*coffeePrice);
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    /**
     * This method displays the given price on the screen.
     */
    private void displayPrice(int number) {
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText(NumberFormat.getCurrencyInstance().format(number));
    }
}
