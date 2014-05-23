package tkd.blackbelt;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Iterator;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlSerializer;

import android.annotation.SuppressLint;
import android.util.Xml;

/**
 * 
 * @author jacoberg2
 *
 */
public class ClassListXMLHandler {

	private ClassList class_list;
	private static final String TITLE_TAG = "class_list";
	private static final String CLASS_TAG = "class";
	private static final String COMP_ATTR = "completed";
	private static final String DATE_ATTR = "date";
	private static final String INSTR_ATTR = "instructor";
	
	/********************
	 * Constructor
	 ********************/
	public ClassListXMLHandler () {
		class_list = ClassList.getClassList ();
	}
	
	/*******************************
	 * getClassList
	 * 
	 * @return
	 *******************************/
	public ClassList getClassList () {
		return class_list;
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
			readClassList (parser);
		}
		finally {
			in.close();
		}
	}

	/***********************************************************
	 * 
	 * @param parser
	 * @throws XmlPullParserException
	 * @throws IOException
	 ***********************************************************/
	@SuppressLint("DefaultLocale")
	private void readClassList (XmlPullParser parser) throws XmlPullParserException, IOException {

		parser.require(XmlPullParser.START_TAG, null, TITLE_TAG);

		while (parser.next() != XmlPullParser.END_TAG) {
			if (parser.getEventType() != XmlPullParser.START_TAG) {
				continue;
			}
			parser.require(XmlPullParser.START_TAG, null, CLASS_TAG);
			
			boolean completed = Boolean.getBoolean(parser.getAttributeValue(null, COMP_ATTR));
			String date = parser.getAttributeValue(null, DATE_ATTR);
			String instructor = parser.getAttributeValue(null, INSTR_ATTR);
			
			class_list.addLeadershipClass(
					new LeadershipClass (completed, instructor, Integer.getInteger(date)));
		}

		parser.require(XmlPullParser.END_TAG, null, TITLE_TAG);
	}
	
	/***************
	 * SERIALIZER
	 ***************/
	
	/****************************************************
	 * 
	 * @param out
	 * @throws IOException
	 ****************************************************/
	public void generateClassListFile (OutputStream out) throws IOException {
		try {
			XmlSerializer serializer = Xml.newSerializer();
			serializer.setOutput(out, null);
			serializer.startDocument(null, Boolean.TRUE);
			serializer.startTag(null, TITLE_TAG);
			writeClassList (serializer);
			serializer.endTag(null, TITLE_TAG);
			serializer.endDocument();
			serializer.flush();
		}
		finally {
			out.close();
		}
	}
	
	/*********************************************************
	 * 
	 * @param serializer
	 * @throws IllegalArgumentException
	 * @throws IllegalStateException
	 * @throws IOException
	 **********************************************************/
	private void writeClassList (XmlSerializer serializer) 
	throws IllegalArgumentException, IllegalStateException, IOException {

		for (Iterator<LeadershipClass> iter = class_list.iterator(); iter.hasNext ();) {
			LeadershipClass lc = iter.next ();
			serializer.startTag(null, CLASS_TAG);
			serializer.attribute(null, COMP_ATTR, Boolean.toString(lc.getCompleted()));
			serializer.attribute(null, DATE_ATTR, lc.getDate().toString());
			serializer.attribute(null, INSTR_ATTR, lc.getInstructor());
			serializer.endTag(null, CLASS_TAG);
		}
	}
}
