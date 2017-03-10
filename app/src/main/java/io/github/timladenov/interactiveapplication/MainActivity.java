package io.github.timladenov.interactiveapplication;

/**
 * Add your package below. Package name can be found in the project's AndroidManifest.xml file.
 * This is the package name our example uses:
 * <p>
 * package com.example.android.justjava;
 */

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {
    /*Button orderButton = (Button) findViewById(R.id.Button1);
    orderButton.setOnClickListener(new View.OnClickListener());*/

    public static final int coffeePrice = 5;
    public int numberOfCoffees = 0;
    public int displayMessages = 0;
    public boolean funnyTotal = false;
    String thankYou = "Free";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            numberOfCoffees = savedInstanceState.getInt("numberOfCoffees");
            displayMessages = savedInstanceState.getInt("displayMessages");
            funnyTotal = savedInstanceState.getBoolean("funnyTotal");
            thankYou = savedInstanceState.getString("thankYou");
        }
        setContentView(R.layout.activity_main);
        display(numberOfCoffees);
        displayPrice(totalPrice(numberOfCoffees, coffeePrice));
        displayMessage(thankYou);
    }

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putInt("numberOfCoffees", numberOfCoffees);
        savedInstanceState.putInt("displayMessages", displayMessages);
        savedInstanceState.putBoolean("funnyTotal", funnyTotal);
        savedInstanceState.putString("thankYou", thankYou);
        super.onSaveInstanceState(savedInstanceState);
    }


    public int totalPrice(int numberOfCoffees, int coffeePrice) {
        return (numberOfCoffees * coffeePrice);
    }

    //View orderView = findViewById(R.id.Button1);

    public void onClick(View orderView) {
        plusOrder(orderView);
    }

    public void onClickMinus(View orderView) {
        minusOrder(orderView);
    }

    public void onClickOrder(View orderview) {
        displayOrder(orderview);
    }

    public boolean setFunny(View funnyTotals) {
        final CheckBox checkbox = (CheckBox) findViewById(R.id.checkbox);
        if (checkbox.isChecked()) {
            return (funnyTotal = true);
        } else {
            return (funnyTotal = false);
        }
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
        if (numberOfCoffees > 0) {
            numberOfCoffees--;
            display(numberOfCoffees);
            //displayPrice(numberOfCoffees*coffeePrice);
        }
    }

    public void displayOrder(View view) {
        displayPrice(totalPrice(numberOfCoffees, coffeePrice));

        if (numberOfCoffees > 0) {
            thankYou = "Thank you!";
            displayMessage(thankYou);
        } else if (numberOfCoffees == 0) {
            thankYou = "Free";
            displayMessage(thankYou);
        }
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
        if (true == funnyTotal) {
            if (number > 0) {

                String[] messages = {"Amount due $" + number + ".", "That would be $" + number + " please.", "You owe " + number + " bucks, dude!", number + " dollars for " + numberOfCoffees + " cups of coffee. Pay up.", "Total = $" + number};
                if (displayMessages < 5) {
                    TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
                    priceTextView.setText("\"" + messages[displayMessages] + "\"");
                    displayMessages++;

                    //priceTextView.setText("\"" + "Amount due:" + NumberFormat.getCurrencyInstance().format(number) + "\"");
                    //priceTextView.setText("\"" + "$ " + number + "\"");
                } else {
                    displayMessages = 0;
                    TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
                    priceTextView.setText("\"" + messages[displayMessages] + "\"");
                    displayMessages++;
                }
            } else {
                TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
                priceTextView.setText("\"" + "Amount due $" + totalPrice(numberOfCoffees, coffeePrice) + "\"");
            }
        } else {
            TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
            priceTextView.setText("Total: " + NumberFormat.getCurrencyInstance().format(number));
        }
    }

    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView thanksTextView = (TextView) findViewById(R.id.thanks_text_view);
        thanksTextView.setText(thankYou);
    }
}