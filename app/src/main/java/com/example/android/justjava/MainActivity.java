/**
 * IMPORTANT: Make sure you are using the correct package name.
 * This example uses the package name:
 * package com.example.android.justjava
 * If you get an error when copying this code into Android studio, update it to match teh package name found
 * in the project's AndroidManifest.xml file.
 **/

package com.example.android.justjava;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity<priceMessage> extends AppCompatActivity {

    private Object priceMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    int quantity = 1;
    int priceOfWhippedCream = 1;
    int  priceOfChoclateCream = 2;

    public void increment(View view) {
        if(quantity < 100)
        {
            quantity = quantity + 1;
        }
        if(quantity >= 100)
        {
            quantity = 100;
        }
        displayQuantity(quantity);
    }
    public void decrement(View view) {
        if(quantity > 1) {
            quantity = quantity - 1;
        }
        if(quantity <= 1)
        {
            quantity = 1;
        }
        displayQuantity(quantity);
    }
    public void submitOrder(View view) {
        CheckBox whippedCreamCheckBox = (CheckBox) findViewById(R.id.whipped_cream_checkbox);
        boolean hasWhippedCream = whippedCreamCheckBox.isChecked();

        CheckBox choclateCreamCheckBox = (CheckBox) findViewById(R.id.choclate_cream_checkbox);
        boolean hasChoclateCream = choclateCreamCheckBox.isChecked();

        int price = calculatePrice(hasWhippedCream,hasChoclateCream);

        EditText simpleEditText = (EditText) findViewById(R.id.name_text_view);
        String strValue = simpleEditText.getText().toString();

        String priceMessage = OrderSummary(price,hasWhippedCream,hasChoclateCream,strValue);
        displayMessage(priceMessage);


        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:jaypatel8914@gmail.com")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_SUBJECT, strValue);
        intent.putExtra(Intent.EXTRA_TEXT, priceMessage);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }


    }

    private String displayMessage(String message) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
        return message;
    }
    private void displayQuantity(int quantity) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + quantity);
    }

    private int calculatePrice(boolean addWhippedCream, boolean addChoclateCream) {
        int baseprice = 5;
        if(addWhippedCream)
        {
            baseprice += priceOfWhippedCream;}
        if(addChoclateCream)
        {
            baseprice += priceOfChoclateCream;}
        return baseprice * quantity;
    }

    private String OrderSummary(int price, boolean whippedCream, boolean choclateCream, String strValue) {
        String priceMessage = " Name " + strValue;
        priceMessage +="\n Add Whipped Cream? " + whippedCream;
        priceMessage +="\n Add Choclate? " + choclateCream;
        priceMessage +="\n Quantity =" + quantity;
        priceMessage +="\n Total Amount = $" + price;
        priceMessage +="\n Thank You";
        return priceMessage;
    }

}