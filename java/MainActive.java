
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */

public class MainActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */

    int quantity = 2;
    
	 public void  increment(View view)
	 {
     display(quantity + 1);
    }   
    
	 public void  decrement(View view)
	 {
      if (quantity > 0)
      { 
        display(quantity - 1);
      }
      else
      {
       display(0);
      }
    }   

	 public void submitOrder(View view)
    { 
      displayPrice(quantity * 5);
    }

    /**
     * This method displays the given price on the screen.
     */
    private void displayPrice(int number)
    {
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText(NumberFormat.getCurrencyInstance().format(number));
    }

    /**
     * This method displays the given quantity value on the screen.
     */

    private void display(int number)
    {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }
}

