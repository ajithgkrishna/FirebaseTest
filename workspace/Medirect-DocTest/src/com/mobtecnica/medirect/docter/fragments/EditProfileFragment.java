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

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.ContentBody;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreProtocolPNames;

import com.mobtecnica.medirect.docter.LoginActivity;
import com.mobtecnica.medirect.docter.R;
import com.mobtecnica.medirect.docter.connection.HttpRequestHelper;
import com.mobtecnica.medirect.docter.interfaces.OnHtttpResponseListenerForUpdateProfile;
import com.mobtecnica.medirect.docter.jsonparsor.JsonParser;
import com.mobtecnica.medirect.docter.models.EditProfile_Model;
import com.mobtecnica.medirect.docter.models.GetAllCitiesModel;
import com.mobtecnica.medirect.docter.models.GetAllCountryModel;
import com.mobtecnica.medirect.docter.models.GetAllStateModel;
import com.mobtecnica.medirect.docter.models.PatientAddModel;
import com.mobtecnica.medirect.docter.models.Profile_Model;
import com.mobtecnica.medirect.docter.utils.Config;
import com.mobtecnica.medirect.docter.utils.Helper;
import com.mobtecnica.medirect.docter.utils.Utilities;
import com.pkmmte.circularimageview.CircularImageView;
import com.squareup.picasso.Picasso;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.DataSetObserver;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.provider.MediaStore.MediaColumns;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class EditProfileFragment extends Fragment {
	EditText editTextFirstName, editTextLastName, editTextDOB,
			editTextAddress1, editTextAddress2, editTextpincode, editTextEmail,
			editTextPhone, editTextcity, editTextstate;

	Button save;
	ImageView calender;
	TextView editTextcountry;
	boolean imageset = false;
	String imgPath = "";
	Bitmap thumbBitmap;
	Spinner gender;
	Bitmap bitmap;
	private String _imageCapturedName = null;
	private File fileName = null;
	CircularImageView pic;
	private int myear;
	private int mMonth;
	private int mDay;
	public static String PREFS_LOGIN_STATUS = "LOGIN";
	public static String PREFS_HAS_LOGIN = "hasLoggedIn";
	public static String PREFS_USERID = "userid";
	private static final int SELECT_PICTURE = 1;
	private EditProfile_Model editmode;
	private ArrayList<GetAllCountryModel> allCountriesList;
	public ArrayList<GetAllStateModel> allStatesList = new ArrayList<GetAllStateModel>();
	public ArrayList<GetAllCitiesModel> allCitiesList = new ArrayList<GetAllCitiesModel>();

	String stringgender = "";
	String cityIdSel = "", genderstring = "";
	String[] gendervalue = { "MALE", "FEMALE" };
	String countryIdSel = "";
	String stateIdSel = "";
	private String countryDialogSelected = "country",
			stateDialogSelected = "state", cityDialogSelected = "city";
	PatientAddModel add_patient_model;
	private Profile_Model doctor;
	// ImageButton imageButtonwallet_nav;
	// ImageButton imageViewmessage_nav;
	ImageButton imageViewuser_nav;
	TextView menuname;

	/*
	 * @Override public void onResume() { initializeViews(getView());
	 * initializeListeners(getView()); buildUI(getView()); super.onResume(); }
	 */
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_edit_profile,
				container, false);
		final Calendar c = Calendar.getInstance();
		myear = c.get(Calendar.YEAR);
		mMonth = c.get(Calendar.MONTH);
		mDay = c.get(Calendar.DAY_OF_MONTH);
		menuname = (TextView) rootView.findViewById(R.id.menuname);
		menuname.setText(getActivity().getResources().getString(
				R.string.menu_profile));
		// display the current date (this method is below)
		// updateDisplay();
		Config.LogError("Edit profile on create ", "list on edit proifle");
		/*Log.e("Esit profile on create ", "list on edit proifle");*/

		initializeViews(rootView);
		initializeListeners(rootView);
		buildUI(rootView);
		return rootView;
	}

	private void buildUI(View rootView) {
		// TODO Auto-generated method stub
		gender.setAdapter(new ArrayAdapter<String>(getActivity(),
				R.layout.spinner_item, gendervalue));
		Bundle bun = getArguments();
		if (!bun.isEmpty()) {
			allCountriesList = bun.getParcelableArrayList("allcountries");
			doctor = bun.getParcelable("DoctorDetails");
		}

		if (doctor != null) {
			Picasso.with(getActivity())
					.load(doctor.getPhoto())
					.placeholder(R.drawable.ic_profile)
					.error(R.drawable.ic_profile).into(pic);

			editTextFirstName.setText(doctor.getFirst_name());
			editTextLastName.setText(doctor.getLast_name());

			/* gender.setText("" + doctor.getGender()); */
			/*
			 * registrationid.setText("Registration Number: " +
			 * doctor.getRegistration_number());
			 * designationtextview.setText("Medical Council: " +
			 * doctor.getMedical_council());
			 * specializationtextview.setText("Qualification: " +
			 * doctor.getQualification());
			 */
			/*
			 * int ageValue = Utilities.getInstance(getActivity()).getAge(
			 * Integer.parseInt(doctor.getDob().split("-")[0]),
			 * Integer.parseInt(doctor.getDob().split("-")[1]),
			 * Integer.parseInt(doctor.getDob().split("-")[2]));
			 * editTextDOB.setText("age: " + ageValue);
			 */
			/* editTextPhone.setText("Phone: " + doctor.getPhone()); */
			editTextAddress1.setText(doctor.getAddress1());
			editTextAddress2.setText(doctor.getAddress2());
			editTextDOB.setText(doctor.getDob());
			editTextcountry.setText(doctor.getCountry_name());
			editTextstate.setText(doctor.getState_name());
			editTextcity.setText(doctor.getCity_name());
			editTextpincode.setText(doctor.getPincode());
			countryIdSel = doctor.getCountry_id();
			stateIdSel = doctor.getState_id();
			cityIdSel = doctor.getCity_id();
			stringgender = doctor.getGender();
			if (doctor.getGender().toLowerCase().equals("male")) {
				gender.setSelection(0);
			} else {
				gender.setSelection(1);
			}

		}

	}

	private void initializeListeners(View rootView) {
		// stringgender
		gender.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				stringgender = gendervalue[position];
				Config.LogError("stringgender", stringgender);
				/*Log.e("stringgender", stringgender);*/

			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub
				stringgender = gendervalue[0];
			}
		});
		editTextcountry.setOnClickListener(new TextView.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				CountryListDialog dialog = new CountryListDialog(getActivity(),
						setDataToCountry(allCountriesList),
						countryDialogSelected);
				dialog.show();
				editTextcity.setClickable(false);
				editTextstate.setClickable(false);
				editTextcity.setText("");
				editTextstate.setText("");
				editTextstate.setHint(R.string.chose_state);
				editTextcity.setHint(R.string.chose_city);
				stateIdSel = "";
				cityIdSel = "";

			}
		});

		editTextstate.setOnClickListener(new EditText.OnClickListener() {

			@Override
			public void onClick(View v) {
				if (!allStatesList.isEmpty()) {
					// TODO Auto-generated method stub
					CountryListDialog dialog = new CountryListDialog(
							getActivity(), setDataToStates(allStatesList),
							stateDialogSelected);
					dialog.show();
					editTextstate.setClickable(true);
					editTextcity.setClickable(false);
					editTextcity.setText("");
					editTextcity.setHint(R.string.chose_city);
					cityIdSel = "";
				} else {
					if (Utilities.getInstance(getActivity()).isNetAvailable()) {
						HttpRequestHelper.getAllStates_Details(getActivity(),
								doctor.getCountry_id(), true);
					} else {
						Toast.makeText(getActivity(), R.string.error_internet,
								Toast.LENGTH_SHORT).show();
					}
				}

			}
		});
		editTextcity.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (!allCitiesList.isEmpty()) {
					// TODO Auto-generated method stub
					CountryListDialog dialog = new CountryListDialog(
							getActivity(), setDataToCities(allCitiesList),
							cityDialogSelected);
					dialog.show();
				} else {
					if (Utilities.getInstance(getActivity()).isNetAvailable()) {
						HttpRequestHelper.getAllCities_Details(getActivity(),
								doctor.getState_id(), true);
					} else {
						Toast.makeText(getActivity(), R.string.error_internet,
								Toast.LENGTH_SHORT).show();
					}
				}
			}
		});

		pic.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				// /image click

				selectImage();

			}
		});

		save.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (!validateFields()) {
					editmode = new EditProfile_Model();
					String userid = PreferenceManager
							.getDefaultSharedPreferences(getActivity())
							.getString("PREFS_USERID",
									"defaultStringIfNothingFound");

					editmode = new EditProfile_Model(userid, editTextFirstName
							.getText().toString(), editTextLastName.getText()
							.toString(), editTextAddress1.getText().toString(),
							editTextAddress2.getText().toString(),
							countryIdSel, cityIdSel, stateIdSel, stringgender,
							editTextDOB.getText().toString(), imgPath,
							editTextpincode.getText().toString().trim());

					new EditProfileAsy().execute();
				}

			}
		});
		calender.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				new DatePickerDialog(getActivity(), mDateSetListener, myear,
						mMonth, mDay).show();
				
			}
		});
		// imageButtonwallet_nav.setOnClickListener(new
		// View.OnClickListener() {
		//
		// @Override
		// public void onClick(View v) {
		// // TODO Auto-generated method stub
		//
		// }
		// });
		// imageViewmessage_nav.setOnClickListener(new
		// View.OnClickListener() {
		//
		// @Override
		// public void onClick(View v) {
		// // TODO Auto-generated method stub
		//
		// }
		// });
		imageViewuser_nav.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				SharedPreferences.Editor editor = getActivity()
						.getSharedPreferences(PREFS_LOGIN_STATUS,
								Context.MODE_PRIVATE).edit();
				editor.clear();
				editor.commit();
				Toast.makeText(getActivity(),
						"You have been Successfully Logged out !",
						Toast.LENGTH_SHORT).show();
				Intent dashBoardIntent = new Intent(getActivity(),
						LoginActivity.class);
				startActivity(dashBoardIntent);
				getActivity().finish();

			}
		});
	}

	private void initializeViews(View rootView) {
		// TODO Auto-generated method stub

		// imageButtonwallet_nav = (ImageButton) rootView
		// .findViewById(R.id.imageButtonwallet_nav);
		// imageViewmessage_nav = (ImageButton) rootView
		// .findViewById(R.id.imageViewmessage_nav);
		imageViewuser_nav = (ImageButton) rootView
				.findViewById(R.id.imageViewuser_nav);
		editTextFirstName = (EditText) rootView
				.findViewById(R.id.editTextFirstName);
		editTextLastName = (EditText) rootView
				.findViewById(R.id.editTextLastName);
		editTextAddress1 = (EditText) rootView
				.findViewById(R.id.editTextAddress1);
		editTextDOB = (EditText) rootView.findViewById(R.id.editTextDOB);
		editTextAddress2 = (EditText) rootView
				.findViewById(R.id.editTextAddress2);
		editTextcity = (EditText) rootView.findViewById(R.id.editTextcity);
		editTextstate = (EditText) rootView.findViewById(R.id.editTextstate);
		editTextcountry = (TextView) rootView
				.findViewById(R.id.editTextcountry);
		editTextpincode = (EditText) rootView
				.findViewById(R.id.editTextpincode);

		calender = (ImageView) rootView.findViewById(R.id.calenderPic);
		gender = (Spinner) rootView.findViewById(R.id.gender);
		save = (Button) rootView.findViewById(R.id.save);
		pic = (CircularImageView) rootView.findViewById(R.id.pic);

	}

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

							editTextcountry.setText(selectedItem);
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
						editTextstate.setText(selectedItem);

						if (Utilities.getInstance(getActivity())
								.isNetAvailable()) {
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
						editTextcity.setText(selectedItem);

						cityIdSel = allCitiesList.get(finalPosition).getId();
					}

				}
			});
			;
		}

	}

	private void updateDisplay() {
		editTextDOB.setText(new StringBuilder()
				// Month is 0 based so add 1
				.append(myear).append("-").append(mMonth + 1).append("-")
				.append(mDay));
	}

	private DatePickerDialog.OnDateSetListener mDateSetListener = new DatePickerDialog.OnDateSetListener() {
		public void onDateSet(DatePicker view, int year, int monthOfYear,
				int dayOfMonth) {
			myear = year;
			mMonth = monthOfYear;
			mDay = dayOfMonth;
			updateDisplay();
		}
	};

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
					/*
					 * Picasso.with(getActivity()).load(createAndGetImgPath(bitmap
					 * )).into(pic);
					 */

				} catch (Exception e) {
					e.printStackTrace();
				}
			} else if (requestCode == 1) {
				Uri selectedImageUri = data.getData();

				String tempPath = getPath(selectedImageUri, getActivity());
				Bitmap bm;
				BitmapFactory.Options btmapOptions = new BitmapFactory.Options();
				bm = BitmapFactory.decodeFile(tempPath, btmapOptions);

				Picasso.with(getActivity()).cancelRequest(pic);
				pic.setImageBitmap(bm);
				imageset = true;
				imgPath = tempPath;
				thumbBitmap = bm;

				/*
				 * Picasso.with(getActivity()).cancelRequest(pic);
				 * Picasso.with(getActivity
				 * ()).load(createAndGetImgPath(bm)).get().
				 */

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
			/*Log.e("storeImagesAndGetPath2", e.getMessage());*/
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

	public class EditProfileAsy extends AsyncTask<Void, Void, String> {
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
				OnHtttpResponseListenerForUpdateProfile login = (OnHtttpResponseListenerForUpdateProfile) getActivity();
				login.onHttpSuccessfulUpdateProfile(result, status_msg, result);
			} else {
				OnHtttpResponseListenerForUpdateProfile login = (OnHtttpResponseListenerForUpdateProfile) getActivity();
				login.onHttpFailedAddUpdateProfile(result, null, status_msg);
			}
		}

		@SuppressWarnings("deprecation")
		@Override
		protected String doInBackground(Void... profil) {
			/* uploadPatientProfile(add_patient_model); */
			HttpPost httppost;
			@SuppressWarnings("deprecation")
			/* public void uploadPatientProfile(PatientAddModel profile) { */
			EditProfile_Model profile = editmode;

			String result = "";
			HttpClient httpclient = new DefaultHttpClient();

			httpclient.getParams().setParameter(
					CoreProtocolPNames.PROTOCOL_VERSION, HttpVersion.HTTP_1_1);
			httppost = new HttpPost(HttpRequestHelper.BASE_URL_EDIT_PROFILE);

			File fileProfPic = null;

			if (!profile.getImage().equalsIgnoreCase("")) {
				fileProfPic = new File(profile.getImage());
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
						new StringBody(profile.getFirstname()));
				mpEntity.addPart("User[last_name]",
						new StringBody(profile.getLastname()));

				mpEntity.addPart("User[address1]",
						new StringBody(profile.getAddress1()));
				mpEntity.addPart("User[address2]",
						new StringBody(profile.getAddress2()));
				mpEntity.addPart("User[city_id]",
						new StringBody(profile.getCityid()));
				mpEntity.addPart("User[state_id]",
						new StringBody(profile.getStateid()));
				mpEntity.addPart("User[country_id]",
						new StringBody(profile.getCountryid()));
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

	public void showStateDialog(ArrayList<GetAllStateModel> rescentStatesList) {
		if (!rescentStatesList.isEmpty()) {
			allStatesList = rescentStatesList;
			CountryListDialog dialog = new CountryListDialog(getActivity(),
					setDataToStates(allStatesList), stateDialogSelected);
			editTextstate.setClickable(true);
			editTextcity.setClickable(false);
			editTextcity.setText("");
			editTextcity.setHint(R.string.chose_city);
			dialog.show();
		}
	}

	public void showCityDialog(ArrayList<GetAllCitiesModel> rescentStatesList) {
		if (!rescentStatesList.isEmpty()) {
			CountryListDialog dialog = new CountryListDialog(getActivity(),
					setDataToCities(allCitiesList), cityDialogSelected);
			dialog.show();
		}
	}

	/**
	 * 
	 * @return true if all fields contains data.
	 */
	private Boolean validateFields() {

		String firstName = editTextFirstName.getText().toString();
		String lastName = editTextLastName.getText().toString();
		String dateOfBirth = editTextDOB.getText().toString();
		String address1 = editTextAddress1.getText().toString();
		String address2 = editTextAddress2.getText().toString();
		String pinCode = editTextpincode.getText().toString();

		View focusView = null;
		Boolean validation = false;

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
		if (!(Utilities.getInstance(getActivity()).isValidPinCode(pinCode))) {
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
		if (TextUtils.isEmpty(countryIdSel)) {
			editTextcountry.setError(getString(R.string.error_field_required));
			focusView = editTextcountry;
			validation = true;
		}
		if (TextUtils.isEmpty(stateIdSel)) {
			editTextstate.setError(getString(R.string.error_field_required));
			focusView = editTextstate;
			validation = true;
		}
		if (TextUtils.isEmpty(cityIdSel)) {
			editTextcity.setError(getString(R.string.error_field_required));
			focusView = editTextcity;
			validation = true;
		}
		if (validation) {
			focusView.requestFocus();
		}
		return validation;
	}

}
