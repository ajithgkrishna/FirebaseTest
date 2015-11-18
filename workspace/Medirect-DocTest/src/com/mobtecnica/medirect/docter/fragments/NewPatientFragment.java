package com.mobtecnica.medirect.docter.fragments;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.ContentBody;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreProtocolPNames;

import com.mobtecnica.medirect.docter.ItemsListActivity;
import com.mobtecnica.medirect.docter.LoginActivity;
import com.mobtecnica.medirect.docter.R;
import com.mobtecnica.medirect.docter.connection.HttpRequestHelper;
import com.mobtecnica.medirect.docter.interfaces.OnHtttpResponseListenerForAddPatient;
import com.mobtecnica.medirect.docter.jsonparsor.JsonParser;
import com.mobtecnica.medirect.docter.models.GetAllCitiesModel;
import com.mobtecnica.medirect.docter.models.GetAllCountryModel;
import com.mobtecnica.medirect.docter.models.GetAllStateModel;
import com.mobtecnica.medirect.docter.models.PatientAddModel;
import com.mobtecnica.medirect.docter.models.PatientModel;
import com.mobtecnica.medirect.docter.models.Profile_Model;
import com.mobtecnica.medirect.docter.utils.Config;
import com.mobtecnica.medirect.docter.utils.Helper;
import com.mobtecnica.medirect.docter.utils.Utilities;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.MediaStore.MediaColumns;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.telephony.PhoneNumberUtils;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class NewPatientFragment extends Fragment {
	private TextView menuname, txtChoosePic;
	private EditText editTextFirstName, editTextLastName, editTextDOB,
			editTextAddress1, editTextAddress2, editTextpincode, editTextEmail,
			editTextPhone, edtPassword;
	private Button save;
	private ImageView calender, pic;
	private int mYear;
	private int mMonth;
	private String _imageCapturedName = null;
	private File fileName = null;
	private int mDay;
	EditText spnCity, spnState;
	private Spinner spnGender;
	private TextView autoCompleteCountry;
	private Bitmap bitmap;
	private boolean imageset = false;
	private String imgPath = "";
	private Bitmap thumbBitmap;
	private ArrayList<GetAllCountryModel> allCountriesList;
	public ArrayList<GetAllStateModel> allStatesList = new ArrayList<GetAllStateModel>();
	public ArrayList<GetAllCitiesModel> allCitiesList = new ArrayList<GetAllCitiesModel>();
	ToggleButton flashlightButton;
	String countryIdSel = "";
	String stateIdSel = "";
	String cityIdSel = "";
	private String countryDialogSelected = "country",
			stateDialogSelected = "state", cityDialogSelected = "city";
	PatientAddModel add_patient_model;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_new_patient,
				container, false);
		menuname = (TextView) rootView.findViewById(R.id.menuname);
		menuname.setText(getActivity().getResources().getString(
				R.string.add_new_patient));
		initializeViews(rootView);
		initializeListeners(rootView);

		final Calendar c = Calendar.getInstance();
		mYear = c.get(Calendar.YEAR);
		mMonth = c.get(Calendar.MONTH);
		mDay = c.get(Calendar.DAY_OF_MONTH);

		// display the current date (this method is below)
		updateDisplay();

		buildUI();
		return rootView;
	}

	private void buildUI() {
		Bundle bun = getArguments();
		allCountriesList = bun.getParcelableArrayList("allcountries");
		if (!allCountriesList.isEmpty()) {
			setDataToCountry(allCountriesList);
		}

	}

	private void initializeListeners(View rootView) {

		save.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (!validateFields()) {
					add_patient_model = new PatientAddModel(editTextFirstName
							.getText().toString(), imgPath, editTextLastName
							.getText().toString(), edtPassword.getText()
							.toString(), editTextEmail.getText().toString(),
							editTextPhone.getText().toString(),
							editTextAddress1.getText().toString(),
							editTextAddress2.getText().toString(), cityIdSel,
							stateIdSel, countryIdSel, editTextpincode.getText()
									.toString(), editTextDOB.getText()
									.toString(), spnGender.getSelectedItem()
									.toString());
					new AddNewPatientAsy().execute();
					// HttpRequestHelper.add_patient(
					// getActivity(),
					// getActivity().getSharedPreferences(
					// LoginActivity.PREFS_LOGIN_STATUS,
					// Context.MODE_PRIVATE).getString(
					// LoginActivity.PREFS_USERID, ""),
					// add_patient_model);
				}

			}
		});
		calender.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				new DatePickerDialog(getActivity(), mDateSetListener, mYear,
						mMonth, mDay).show();

			}
		});
		pic.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				selectImage();
			}
		});

		autoCompleteCountry.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				CountryListDialog dialog = new CountryListDialog(getActivity(),
						setDataToCountry(allCountriesList),
						countryDialogSelected);
				dialog.show();
				spnState.setClickable(false);
				spnCity.setClickable(false);
				spnState.setText("");
				spnCity.setText("");
				spnState.setHint(R.string.chose_state);
				spnCity.setHint(R.string.chose_city);
			}
		});
		spnState.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (allStatesList != null) {
					// TODO Auto-generated method stub
					/*
					 * StatesListDialog dialog = new StatesListDialog(
					 * getActivity(), setDataToStates(allStatesList));
					 */
					CountryListDialog dialog = new CountryListDialog(
							getActivity(), setDataToStates(allStatesList),
							stateDialogSelected);
					dialog.show();
					spnState.setClickable(true);
					spnCity.setClickable(false);
					spnCity.setText("");
					spnCity.setHint(R.string.chose_city);
				}

			}
		});
		spnCity.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (allCitiesList != null) {
					// TODO Auto-generated method stub
					/*
					 * CitiesListDialog dialog = new CitiesListDialog(
					 * getActivity(), setDataToCities(allCitiesList));
					 */
					CountryListDialog dialog = new CountryListDialog(
							getActivity(), setDataToCities(allCitiesList),
							cityDialogSelected);
					dialog.show();
				}
			}
		});

	}

	private void initializeViews(View rootView) {
		txtChoosePic = (TextView) rootView.findViewById(R.id.txtChosePic);
		editTextFirstName = (EditText) rootView.findViewById(R.id.edtFirstName);
		editTextLastName = (EditText) rootView.findViewById(R.id.edtLastname);
		editTextAddress1 = (EditText) rootView.findViewById(R.id.edtAddress1);
		edtPassword = (EditText) rootView.findViewById(R.id.edtPassword);
		editTextDOB = (EditText) rootView.findViewById(R.id.edtDateOfBirth);
		editTextAddress2 = (EditText) rootView.findViewById(R.id.edtAddress2);
		spnCity = (EditText) rootView.findViewById(R.id.spnCity);
		spnState = (EditText) rootView.findViewById(R.id.spnState);
		autoCompleteCountry = (TextView) rootView
				.findViewById(R.id.autoCompleteCountry);
		editTextpincode = (EditText) rootView.findViewById(R.id.edtPincode);
		editTextEmail = (EditText) rootView.findViewById(R.id.editTextEmail);
		editTextPhone = (EditText) rootView.findViewById(R.id.edtPhoneNumber);
		calender = (ImageView) rootView.findViewById(R.id.calender);
		pic = (ImageView) rootView.findViewById(R.id.pic);
		spnGender = (Spinner) rootView.findViewById(R.id.spnGender);
		save = (Button) rootView.findViewById(R.id.save);

	}

	/******
	 * Dialog for showing the list of countries,states and cities.
	 * 
	 * @author user
	 */
	public class CountryListDialog extends Dialog {
		private ListView lstDialogConfirm;
		private EditText edtSearch;
		private ArrayList<String> countryDuplicate = new ArrayList<String>();
		private ArrayList<String> finalArray = new ArrayList<String>();
		private int textlenght = 0;
		private Context con;
		private String selectedDialog;

		/*****
		 * Depending on this constructor the country,state and city loading
		 * takes place.
		 * 
		 * @param context
		 * @param finalArray
		 * @param selectedDialog
		 *            this value helps to determine whether it is country ,
		 *            state or city.
		 */
		public CountryListDialog(Context context, ArrayList<String> finalArray,
				String selectedDialog) {
			super(context);
			// TODO Auto-generated constructor stub
			this.finalArray = finalArray;
			this.con = context;
			this.selectedDialog = selectedDialog;
		}

		@Override
		protected void onCreate(Bundle savedInstanceState) {
			// TODO Auto-generated method stub
			super.onCreate(savedInstanceState);
			requestWindowFeature(Window.FEATURE_NO_TITLE);
			setContentView(R.layout.dialog_country_list);
			lstDialogConfirm = (ListView) findViewById(R.id.lstCountry);
			edtSearch = (EditText) findViewById(R.id.edtSearchName);
			lstDialogConfirm.setAdapter(new ArrayAdapter<String>(con,
					R.layout.spinner_item, finalArray));
			edtSearch.addTextChangedListener(new TextWatcher() {

				@Override
				public void onTextChanged(CharSequence s, int start,
						int before, int count) {
					// TODO Auto-generated method stub
					textlenght = edtSearch.getText().length();
					countryDuplicate.clear();
					for (int i = 0; i < finalArray.size(); i++) {
						if (textlenght <= finalArray.get(i).length()) {
							if (edtSearch
									.getText()
									.toString()
									.equalsIgnoreCase(
											(String) finalArray.get(i)
													.subSequence(0, textlenght))) {
								countryDuplicate.add(finalArray.get(i));
							}
						}
					}
					lstDialogConfirm.setAdapter(new ArrayAdapter<String>(con,
							R.layout.spinner_item, countryDuplicate));

				}

				@Override
				public void beforeTextChanged(CharSequence s, int start,
						int count, int after) {
					// TODO Auto-generated method stub
				}

				@Override
				public void afterTextChanged(Editable s) {
					// TODO Auto-generated method stub
				}
			});

			lstDialogConfirm.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {
					// TODO Auto-generated method stub
					dismiss();
					String selectedItem = (String) lstDialogConfirm
							.getItemAtPosition(position);
					int finalPosition = finalArray.indexOf(selectedItem);

					if (selectedDialog.equals(countryDialogSelected)) {
						if (Utilities.getInstance(getActivity())
								.isNetAvailable()) {

							autoCompleteCountry.setText(selectedItem);
							HttpRequestHelper.getAllStates_Details(
									getActivity(),

									allCountriesList.get(finalPosition)
											.getCountry_id(), false);
							countryIdSel = allCountriesList.get(finalPosition)
									.getCountry_id();
						} else {
							Toast.makeText(getActivity(),
									R.string.error_internet, Toast.LENGTH_SHORT)
									.show();
						}
					} else if (selectedDialog.equals(stateDialogSelected)) {
						spnState.setText(selectedItem);

						if (Utilities.getInstance(getActivity())
								.isNetAvailable()) {
							// false for show dialog
							HttpRequestHelper.getAllCities_Details(
									getActivity(),
									allStatesList.get(finalPosition).getId(),
									false);
							stateIdSel = allStatesList.get(finalPosition)
									.getId();
						} else {
							Toast.makeText(getActivity(),
									R.string.error_internet, Toast.LENGTH_SHORT)
									.show();
						}
					} else if (selectedDialog.equals(cityDialogSelected)) {
						spnCity.setText(selectedItem);

						cityIdSel = allCitiesList.get(finalPosition).getId();
					}

				}
			});
			;
		}

	}

	/****
	 * to update the selected date from calendar to EditText
	 */
	private void updateDisplay() {
		editTextDOB.setText(new StringBuilder()
				// Month is 0 based so add 1
				.append(mYear).append("-").append(mMonth + 1).append("-")
				.append(mDay).append(" "));
	}

	/****
	 * to show the DatePicker dialog when the user clicks on the calendar image.
	 */
	private DatePickerDialog.OnDateSetListener mDateSetListener = new DatePickerDialog.OnDateSetListener() {
		public void onDateSet(DatePicker view, int year, int monthOfYear,
				int dayOfMonth) {
			mYear = year;
			mMonth = monthOfYear;
			mDay = dayOfMonth;
			updateDisplay();
		}
	};

	/*****
	 * select image from gallery or camera
	 */
	private void selectImage() {
		// TODO Auto-generated method stub
		final CharSequence[] items = { "Take Photo", "Choose from Library",
				"Cancel" };

		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		builder.setTitle("Add Photo!");
		builder.setItems(items, new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int item) {
				if (items[item].equals("Take Photo")) {

					Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
					_imageCapturedName = "Image_"
							+ String.valueOf(System.currentTimeMillis());
					fileName = Helper.createFileInSDCard(Helper.getTempFile()
							+ "=TestFolder/", _imageCapturedName + ".JPG");

					intent = new Intent("android.media.action.IMAGE_CAPTURE")
							.putExtra(android.provider.MediaStore.EXTRA_OUTPUT,
									Uri.fromFile(new File(fileName.toString())));

					startActivityForResult(intent, 0);
				} else if (items[item].equals("Choose from Library")) {
					Intent intent = new Intent(
							Intent.ACTION_PICK,
							android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
					intent.setType("image/*");
					startActivityForResult(

					Intent.createChooser(intent, "Select File"), 1);
				} else if (items[item].equals("Cancel")) {
					dialog.dismiss();
				}
			}
		});
		builder.show();
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);

		if (resultCode == getActivity().RESULT_OK) {
			if (requestCode == 0) {

				try {

					BitmapFactory.Options bmOptions = new BitmapFactory.Options();
					bitmap = BitmapFactory.decodeFile(fileName.toString(),
							bmOptions);
					imageset = true;
					pic.setImageBitmap(bitmap);
					imgPath = fileName.getAbsolutePath();

					Config.LogError("PATH", imgPath);
					/*Log.e("PATH", imgPath);*/
					Bitmap ThumbImage = ThumbnailUtils.extractThumbnail(
							BitmapFactory.decodeFile(imgPath), 400, 400);
					pic.setImageBitmap(ThumbImage);
					imageset = true;
					thumbBitmap = ThumbImage;

				} catch (Exception e) {
					e.printStackTrace();
				}
			} else if (requestCode == 1) {
				Uri selectedImageUri = data.getData();

				String tempPath = getPath(selectedImageUri, getActivity());
				Bitmap bm;
				BitmapFactory.Options btmapOptions = new BitmapFactory.Options();
				bm = BitmapFactory.decodeFile(tempPath, btmapOptions);
				pic.setImageBitmap(bm);
				imageset = true;
				imgPath = tempPath;
				thumbBitmap = bm;

			}
		}
	}

	public String createAndGetImgPath(Bitmap bitmap) {
		String path = "";
		ByteArrayOutputStream bytes = new ByteArrayOutputStream();
		bitmap.compress(Bitmap.CompressFormat.JPEG, 40, bytes);

		// you can create a new file name "test.jpg" in sdcard folder.

		File f = new File(Environment.getExternalStorageDirectory()
				+ File.separator + "img" + System.currentTimeMillis() + ".jpg");
		try {
			f.createNewFile();
			// write the bytes in file
			f.getParentFile().mkdir();
			FileOutputStream fo = new FileOutputStream(f);
			fo.write(bytes.toByteArray());

			// remember close de FileOutput
			fo.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Config.LogError("storeImagesAndGetPath", e.getMessage());
			/*Log.e("storeImagesAndGetPath", e.getMessage());*/
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Config.LogError("storeImagesAndGetPath2", e.getMessage());
		/*	Log.e("storeImagesAndGetPath2", e.getMessage());*/
		}

		path = f.getAbsolutePath();

		return path;
	}

	private String getPath(Uri selectedImageUri, FragmentActivity activity) {
		// TODO Auto-generated method stub
		String[] projection = { MediaColumns.DATA };
		Cursor cursor = activity.managedQuery(selectedImageUri, projection,
				null, null, null);
		int column_index = cursor.getColumnIndexOrThrow(MediaColumns.DATA);
		cursor.moveToFirst();
		return cursor.getString(column_index);
	}

	private ArrayList<String> setDataToCountry(
			ArrayList<GetAllCountryModel> allCountries) {
		ArrayList<String> countryNameList = new ArrayList<String>();

		for (int i = 0; i < allCountries.size(); i++) {
			countryNameList.add(allCountries.get(i).getCountry_name());
		}

		return countryNameList;
	}

	public ArrayList<String> setDataToStates(
			ArrayList<GetAllStateModel> allstates) {
		ArrayList<String> stateNameList = new ArrayList<String>();

		for (int i = 0; i < allstates.size(); i++) {
			stateNameList.add(allstates.get(i).getName());
		}

		return stateNameList;
	}

	public ArrayList<String> setDataToCities(
			ArrayList<GetAllCitiesModel> allcities) {
		ArrayList<String> cityNameList = new ArrayList<String>();

		for (int i = 0; i < allcities.size(); i++) {
			cityNameList.add(allcities.get(i).getName());
		}

		return cityNameList;
	}

	/**
	 * 
	 * @return true if all fields contains data.
	 */
	private Boolean validateFields() {
		String email = editTextEmail.getText().toString();
		String password = edtPassword.getText().toString();
		String firstName = editTextFirstName.getText().toString();
		String lastName = editTextLastName.getText().toString();
		String pinCode = editTextpincode.getText().toString();
		String address1 = editTextAddress1.getText().toString();
		String address2 = editTextAddress2.getText().toString();
		String dateOfBirth = editTextDOB.getText().toString();
		String phoneNumber = editTextPhone.getText().toString();
		View focusView = null;
		Boolean validation = false;

		if (TextUtils.isEmpty(email)) {
			editTextEmail.setError(getString(R.string.error_field_required));
			focusView = editTextEmail;
			validation = true;
		}
		if (TextUtils.isEmpty(password)) {
			edtPassword.setError(getString(R.string.error_field_required));
			focusView = edtPassword;
			validation = true;
		}
		if (TextUtils.isEmpty(firstName)) {
			editTextFirstName
					.setError(getString(R.string.error_field_required));
			focusView = editTextFirstName;
			validation = true;
		}
		if (TextUtils.isEmpty(lastName)) {
			editTextLastName.setError(getString(R.string.error_field_required));
			focusView = editTextLastName;
			validation = true;
		}
		if (TextUtils.isEmpty(pinCode)) {
			editTextpincode.setError(getString(R.string.error_field_required));
			focusView = editTextpincode;
			validation = true;
		}
		if(!(Utilities.getInstance(getActivity()).isValidPinCode(pinCode))){
			editTextpincode.setError(getString(R.string.error_field_required));
			focusView = editTextpincode;
			validation = true;
		}
		if (TextUtils.isEmpty(address1)) {
			editTextAddress1.setError(getString(R.string.error_field_required));
			focusView = editTextAddress1;
			validation = true;
		}
		if (TextUtils.isEmpty(address2)) {
			editTextAddress2.setError(getString(R.string.error_field_required));
			focusView = editTextAddress2;
			validation = true;
		}
		if (TextUtils.isEmpty(dateOfBirth)) {
			editTextDOB.setError(getString(R.string.error_field_required));
			focusView = editTextDOB;
			validation = true;
		}
		if (TextUtils.isEmpty(phoneNumber)) {
			editTextPhone.setError(getString(R.string.error_field_required));
			focusView = editTextPhone;
			validation = true;
		}
		if (!(Utilities.getInstance(getActivity()).isValidMobile(phoneNumber))) {
			editTextPhone.setError(getString(R.string.error_field_required));
			focusView = editTextPhone;
			validation = true;
		}
		if (TextUtils.isEmpty(countryIdSel)) {
			autoCompleteCountry.setError(getString(R.string.error_field_required));
			focusView = autoCompleteCountry;
			validation = true;
		}
		if (TextUtils.isEmpty(stateIdSel)) {
			spnState.setError(getString(R.string.error_field_required));
			focusView = spnState;
			validation = true;
		}
		if (TextUtils.isEmpty(cityIdSel)) {
			spnCity.setError(getString(R.string.error_field_required));
			focusView = spnCity;
			validation = true;
		}
		if (validation) {
			focusView.requestFocus();
		}
		return validation;
	}

	public class AddNewPatientAsy extends AsyncTask<Void, Void, String> {
		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
		}

		@Override
		protected void onPostExecute(String result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			System.out.println("onPostExecute");
			boolean status_msg = JsonParser.getInstance().checkresponsestatus(
					result);
			if (status_msg) {
				OnHtttpResponseListenerForAddPatient login = (OnHtttpResponseListenerForAddPatient) getActivity();
				login.onHttpSuccessfulAddPatient(result, JsonParser
						.getInstance().checkresponsestatus(result), JsonParser
						.getInstance().parseaddPatientResponse(result));
			} else {
				OnHtttpResponseListenerForAddPatient login = (OnHtttpResponseListenerForAddPatient) getActivity();
				login.onHttpFailedAddPatient(result, null, JsonParser
						.getInstance().checkresponsestatus(result));
			}
		}

		@SuppressWarnings("deprecation")
		@Override
		protected String doInBackground(Void... profil) {
			/* uploadPatientProfile(add_patient_model); */
			HttpPost httppost;
			@SuppressWarnings("deprecation")
			/* public void uploadPatientProfile(PatientAddModel profile) { */
			PatientAddModel profile = add_patient_model;

			String result = "";
			HttpClient httpclient = new DefaultHttpClient();

			httpclient.getParams().setParameter(
					CoreProtocolPNames.PROTOCOL_VERSION, HttpVersion.HTTP_1_1);
			httppost = new HttpPost(HttpRequestHelper.BASE_URL_CREATE_PATIENT);

			File fileProfPic = null;

			if (!profile.getImageFile().equalsIgnoreCase("")) {
				fileProfPic = new File(profile.getImageFile());
			}

			MultipartEntity mpEntity = new MultipartEntity();
			ContentBody cbFileProfPic = null;

			if (fileProfPic != null) {
				cbFileProfPic = new FileBody(fileProfPic);
			}

			if (cbFileProfPic != null) {
				Config.LogError("FILE LENGTH", cbFileProfPic.getContentLength() + "");
				/*Log.e("FILE LENGTH", cbFileProfPic.getContentLength() + "");*/
				mpEntity.addPart("ImageUpload[imageFile]", cbFileProfPic);
			}
			try {
				mpEntity.addPart(
						"id",
						new StringBody(getActivity().getSharedPreferences(
								LoginActivity.PREFS_LOGIN_STATUS,
								Context.MODE_PRIVATE).getString(
								LoginActivity.PREFS_USERID, "")));
				mpEntity.addPart("User[first_name]",
						new StringBody(profile.getFirst_name()));
				mpEntity.addPart("User[last_name]",
						new StringBody(profile.getLast_name()));
				mpEntity.addPart("User[password]",
						new StringBody(profile.getPassword()));

				mpEntity.addPart("User[email]",
						new StringBody(profile.getEmail()));
				mpEntity.addPart("User[phone]",
						new StringBody(profile.getPhone()));

				mpEntity.addPart("User[address1]",
						new StringBody(profile.getAddress1()));
				mpEntity.addPart("User[address2]",
						new StringBody(profile.getAddress2()));
				mpEntity.addPart("User[city_id]",
						new StringBody(profile.getCity_id()));
				mpEntity.addPart("User[state_id]",
						new StringBody(profile.getState_id()));
				mpEntity.addPart("User[country_id]",
						new StringBody(profile.getCountry_id()));
				mpEntity.addPart("User[pincode]",
						new StringBody(profile.getPincode()));
				mpEntity.addPart("User[dob]", new StringBody(profile.getDob()
						.toString().trim()));
				mpEntity.addPart("User[gender]",
						new StringBody(profile.getGender()));

			} catch (UnsupportedEncodingException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				Config.LogError("ERROR1", e1 + "");
				/*Log.e("ERROR1", e1 + "");*/

			}

			httppost.setEntity(mpEntity);

			System.out
					.println("executing request " + httppost.getRequestLine());
			try {
				HttpResponse response = httpclient.execute(httppost);
				HttpEntity resEntity = response.getEntity();
				StringBuilder builder = new StringBuilder();
				if (resEntity != null) {

					InputStream content = resEntity.getContent();
					BufferedReader reader = new BufferedReader(
							new InputStreamReader(content));
					String line;
					while ((line = reader.readLine()) != null) {
						builder.append(line);

					}
					result = builder.toString();
				}

				Config.LogError("RESPONSE", builder.toString());
				/*Log.e("RESPONSE", builder.toString());*/

			} catch (ClientProtocolException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				Config.LogError("ERROR2", e + "");
				/*Log.e("ERROR2", e + "");*/
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				Config.LogError("ERROR3", e + "");
				/*Log.e("ERROR3", e + "");*/
			}

			return result;
		}

	}

}
