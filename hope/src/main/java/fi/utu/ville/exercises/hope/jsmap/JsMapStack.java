package fi.utu.ville.exercises.hope.jsmap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.vaadin.ui.AbstractJavaScriptComponent;
import com.vaadin.ui.JavaScriptFunction;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

import fi.utu.ville.exercises.hope.HopeExecutor;

@com.vaadin.annotations.JavaScript({"public/initMaps.js" })

public class JsMapStack extends AbstractJavaScriptComponent {
	private HopeExecutor exect;
	public JsMapStack(final HopeExecutor exect) {
		this.exect = exect;
		addFunction("sendCountryAnswer", new JavaScriptFunction() { 

		      public void call(JSONArray arguments) throws JSONException {
		        
		        String arvo = arguments.getString(0);
                
		        exect.setCorrect(arvo);
		        exect.checkAnswer(arvo);
		        exect.moveToNextQuestion();
		        /*getState().points = args.get("points").toString(); 
		        getState().tries = args.get("tries").toString(); 
		        points = getState().points; 
		        tries = getState().tries; */
		      }
		    });
	}
		
		
		
	

}
