package tkd.blackbelt;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlSerializer;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Xml;

/***********************************
 * 
 * @author jacoberg2
 *
 ***********************************/
@SuppressLint("DefaultLocale")
public class ChecklistXMLHandler {

	private Checklist check_list;
	private static final String TITLE_TAG = "checklist";
	private static final String ATTR_NAME = "complete";

	/*********************
	 * 
	 *********************/
	public ChecklistXMLHandler () {
		check_list = Checklist.getChecklist();
	}

	/**********************
	 * 
	 * @return
	 **********************/
	public Checklist getChecklist () {
		return check_list;
	}

	/****************
	 * PARSER
	 ****************/
	
	/**********************************************************************************************
	 * 
	 * @param in
	 * @throws XmlPullParserException
	 * @throws IOException
	 *********************************************************************************************/
	public void parse (InputStream in) 
	throws XmlPullParserException, IOException {
		try {
			XmlPullParser parser = Xml.newPullParser();
			parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
			parser.setInput(in, null);
			parser.nextTag();
			readChecklist (parser);
		}
		finally {
			in.close();
		}
	}

	/****************************************
	 * 
	 * @param parser
	 * @throws XmlPullParserException
	 * @throws IOException
	 ****************************************/
	@SuppressLint("DefaultLocale")
	private void readChecklist (XmlPullParser parser) throws XmlPullParserException, IOException {

		parser.require(XmlPullParser.START_TAG, null, TITLE_TAG);

		while (parser.next() != XmlPullParser.END_TAG) {
			if (parser.getEventType() != XmlPullParser.START_TAG) {
				continue;
			}
			String name = parser.getName();
			Checklist.Items item = Checklist.Items.valueOf(name.toUpperCase());
			if (item != null) {
				String comp_str = null;
				parser.getAttributeValue(ATTR_NAME, comp_str);
				boolean completed = Boolean.parseBoolean(comp_str);
				
				if (completed) {
					check_list.itemCompleted(item);
				}
				else {
					check_list.resetItem(item);
				}
			}
		}

		parser.require(XmlPullParser.END_TAG, null, TITLE_TAG);
	}
	
	/****************
	 * SERIALIZER
	 ****************/
	
	/**********************************
	 * 
	 * @param out
	 * @throws IOException
	 **********************************/
	public void generateChecklistFile (OutputStream out) throws IOException {
		try {
			XmlSerializer serializer = Xml.newSerializer();
			serializer.setOutput(out, null);
			serializer.startDocument(null, Boolean.TRUE);
			serializer.startTag(null, TITLE_TAG);
			writeChecklist (serializer);
			serializer.endTag(null, TITLE_TAG);
			serializer.endDocument();
			serializer.flush();
		}
		finally {
			out.close();
		}
	}
	
	/************************************************
	 * 
	 * @param serializer
	 * @throws IllegalArgumentException
	 * @throws IllegalStateException
	 * @throws IOException
	 ************************************************/
	private void writeChecklist (XmlSerializer serializer) 
	throws IllegalArgumentException, IllegalStateException, IOException {

		for (Checklist.Items item : Checklist.Items.values()) {
			boolean entry = check_list.isCompleted(item);
			serializer.startTag(null, item.toString().toLowerCase());
			serializer.attribute(null, ATTR_NAME, Boolean.toString(entry));
			serializer.endTag(null, item.toString().toLowerCase());
		}
	}
	
	/**
	 * 
	 * @param args
	 */
//	public static void main (String[] args) {
//		String file_name = "~/git/ms-tkd-bb-app/checklist.xml";
//		
//		try {
//			FileInputStream input = new FileInputStream (file_name);
//			ChecklistXMLParserSerializer ps = new ChecklistXMLParserSerializer ();
//			Checklist list = ps.getChecklist ();
//			
//			ps.parse (input);
//			input.close ();
//			
//			for (Checklist.Items item : Checklist.Items.values ()) {
//				System.out.println(item.toString () + " : " + list.isCompleted (item));
//				
//				if (item.ordinal () % 2 == 0) {
//					list.itemCompleted (item);
//				}
//				else {
//					list.resetItem (item);
//				}
//			}
//			
//			FileOutputStream output = new FileOutputStream (file_name);
//			
//			ps.generateChecklistFile (output);
//			output.close ();
//			
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (XmlPullParserException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
}
