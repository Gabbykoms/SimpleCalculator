package com.example.gcdcalculator;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;
import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    TextView result, solution;
    MaterialButton buttonC, buttonBracketOpen, buttonBracketClose;
    MaterialButton buttonDivide, buttonMultiply, buttonPlus, buttonMinus, buttonEquals;
    MaterialButton button7, button8, button9, button4, button5, button6, button1, button2, button3, button0;
    MaterialButton buttonAC, buttonDot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        result=findViewById(R.id.result);
        solution=findViewById(R.id.solution);

        assigId(buttonC, R.id.button_C);
        assigId(button9, R.id.button_9);
        assigId(button8, R.id.button_8);
        assigId(button7, R.id.button_7);
        assigId(button6, R.id.button_6);
        assigId(button5, R.id.button_5);
        assigId(button4, R.id.button_4);
        assigId(button3, R.id.button_3);
        assigId(button2, R.id.button_2);
        assigId(button1, R.id.button_1);
        assigId(button0, R.id.button_0);
        assigId(buttonBracketClose, R.id.button_close_bracket);
        assigId(buttonBracketOpen, R.id.button_open_bracket);
        assigId(buttonMultiply, R.id.button_multiply);
        assigId(buttonAC, R.id.button_AC);
        assigId(buttonDot, R.id.button_dot);
        assigId(buttonDivide, R.id.button_divide);
        assigId(buttonMinus, R.id.button_subtract);
        assigId(buttonPlus, R.id.button_add);
        assigId(buttonEquals, R.id.button_equal);
    }
    void assigId(MaterialButton btn, int id){
        btn=findViewById(id);
        btn.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        MaterialButton button= (MaterialButton) view;
        String buttonText = button.getText().toString();
//        solution.setText(buttonText);
        String dataToCalculate = solution.getText().toString();

        if(buttonText.equals("AC")){
            solution.setText("");
            result.setText("0");
            return;

        }
        if(buttonText.equals("=")) {
            solution.setText(result.getText());
            return;
        }

        if(buttonText.equals("C")) {
            dataToCalculate= dataToCalculate.substring(0, dataToCalculate.length()-1);
        }else{
            dataToCalculate= dataToCalculate+buttonText;
        }
        solution.setText(dataToCalculate);

        String finalResult = getResult(dataToCalculate);
        if(!finalResult.equals("Err")){
            result.setText(finalResult);
        }
    }
    String getResult(String data){
        try{
            Context context = Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable = context.initSafeStandardObjects();
           String finalResult=context.evaluateString(scriptable, data,"JavaScript",1, null).toString();
           if(finalResult.endsWith(".0")){
               finalResult = finalResult.replace(".0", "");
           }
           return finalResult;
        }catch(Exception e){
            return"Err";
        }

    }
}