package meow.rayjackson.shaper;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.support.v7.widget.AppCompatSpinner;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by rayjackson on 27.05.17.
 */

public class Figure {
    private String name;
    private String info;
    private int color;
    private int shape;
    private double density;

    public Figure(String name, String info, int shape, int color, double density) {
        this.name = name;
        this.info = info;
        this.color = color;
        this.shape = shape;
        this.density = density;
    }

    public Figure(String json){
        JSONObject object = null;
        try{
            object = new JSONObject(json);
        }catch (JSONException e){
            e.printStackTrace();
        }
        if(object != null){
            this.name = object.optString("name");
            this.info = object.optString("info");
            this.shape = object.optInt("shape");
            this.color = object.optInt("color");
            this.density = object.optDouble("density");
        }
    }

    public String getName() {
        return this.name;
    }

    public String getInfo() {
        return this.info;
    }

    public Drawable getDrawable() {
        GradientDrawable drawable = new GradientDrawable();
        setShape(drawable, shape);
        setColor(drawable, color);
        drawable.setSize((int) (72 * density), (int) (72 * density));
        return drawable;
    }

    private void setShape(GradientDrawable drawable, int shape) {
        switch (shape) {
            case 0:
                drawable.setShape(GradientDrawable.RECTANGLE);
                break;
            case 1:
                drawable.setShape(GradientDrawable.OVAL);
                break;
            case 2:
                drawable.setShape(GradientDrawable.LINE);
                break;
        }
    }

    private void setColor(GradientDrawable drawable, int color) {
        switch (color) {
            case 0:
                if(shape == 2){
                    drawable.setStroke((int)(4*density), Color.RED);
                }
                drawable.setColor(Color.RED);
                break;
            case 1:
                if(shape == 2){
                    drawable.setStroke((int)(4*density), Color.GREEN);
                }
                drawable.setColor(Color.GREEN);
                break;
            case 2:
                if(shape == 2){
                    drawable.setStroke((int)(4*density), Color.BLUE);
                }
                drawable.setColor(Color.BLUE);
                break;
        }
    }

    public String convertToString() {
        JSONObject json = new JSONObject();
        try {
            json.put("name", name);
            json.put("info", info);
            json.put("shape", shape);
            json.put("color", color);
            json.put("density", density);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return json.toString();
    }

}
