package com.mobtecnica.medirect.docter.jsonparsor;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.mobtecnica.medirect.docter.models.Profile_Model;
import com.mobtecnica.medirect.docter.models.RecentPrescriptions;
import com.mobtecnica.medirect.docter.utils.Config;


/****
 * The jsonParser for listing data in HomePage Fragment.
 * @author Diljith
 *
 */
public class JsonParserForHomePatient {

	private static String TAG = "HomePageFragmentJSONParsing";
	
	public static ArrayList<Profile_Model> parsegetRecentMessagesResponseHome(
			String response) {
		Config.LogError(TAG, "@parsegetallProfileResponse");
		String message = "";
		JSONObject jsonObj;
		ArrayList<Profile_Model> decentmsg = new ArrayList<Profile_Model>();
		try {
			jsonObj = new JSONObject(response);
			String statusval = jsonObj.getString("status");

			if (statusval.equalsIgnoreCase("1")) {

				JSONArray resultArray = jsonObj.getJSONArray("data");
				for (int i = 0; i < resultArray.length(); i++) {
					JSONObject result = resultArray.getJSONObject(i);
					decentmsg.add(new Profile_Model(result.getString("id"),result.getString("account_no")
							,result.getString("first_name")
							,result.getString("last_name")
							,result.getString("address")
							,result.getString("email")
							,result.getString("phone")
							,result.getString("gender")
							,result.getString("age"),result.getString("last_visit"),result.getString("photo")
							));
				}

			} else {

				message = jsonObj.getString("status");
			}

		} catch (JSONException e) {
			e.printStackTrace();
		}
		Config.LogError(TAG, "parsegetRecentMessagesResponseHome message = "
				+ message);
		return decentmsg;
	}
}
