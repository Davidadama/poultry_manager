package com.example.android.poultry_manager;

import android.content.res.AssetFileDescriptor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.tensorflow.lite.Interpreter;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

public class layersEstimate extends AppCompatActivity {

    EditText inputNumber;
    Button inferButtton;
    TextView outputNumber;
    Interpreter tflite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layers_estimate);
        inputNumber = (EditText)findViewById(R.id.inputNumber);
        outputNumber = (TextView)findViewById(R.id.outputNumber);
        inferButtton = (Button)findViewById(R.id.inferButton);

        try {
           tflite = new Interpreter(LoadModelFile());
        }catch (Exception ex){
            ex.printStackTrace();
        }

        inferButtton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                float prediction  = doInference(inputNumber.getText().toString());
                outputNumber.setText(Float.toString(prediction));
            }
        });
    }
    public float doInference(String inputString){
        //input shape is [1]
        float[] inputVal = new float[1];
        inputVal[0] = Float.valueOf(inputString);
        //output shape is[1][1]
        float [][] outputVal = new float[1][1];
        //run inference passing the input shape and getting the output shape
        tflite.run(inputVal,  outputVal);
        //inferred value is at [0][0]
        float inferredValue = outputVal[0][0];

        return inferredValue;

    }

    private MappedByteBuffer LoadModelFile() throws IOException{
        AssetFileDescriptor fileDescriptor = this.getAssets().openFd( "EstimationLayers.tflite");
        FileInputStream inputStream = new FileInputStream(fileDescriptor.getFileDescriptor());
        FileChannel fileChannel = inputStream.getChannel();
        Long startOffset  =fileDescriptor.getStartOffset();
        Long declaredLength  = fileDescriptor.getDeclaredLength();
        return fileChannel.map(FileChannel.MapMode.READ_ONLY, startOffset,declaredLength);
    }


}

