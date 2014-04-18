package tkd.blackbelt;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Iterator;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlSerializer;

import android.util.Xml;

/**
 * Form XML File Parser and Serializer takes care of reading and writing the Form object from
 * and to the creative_form.xml file.
 * 
 * @author jacoberg2
 *
 */
public class FormXMLParserSerializer {
	
	private Form form;
	private static final String TITLE_TAG		= "creative_form";
	private static final String STEP_TAG		= "step";
	private static final String STANCE_TAG		= "stance";
	private static final String DIRECTION_TAG	= "direction";
	private static final String HAND_TECH_TAG	= "hand_technique";
	private static final String FOOT_TECH_TAG	= "foot_technique";
	private static final String NUMBER_ATTR		= "number";
	
	/**
	 * Constructor
	 */
	public FormXMLParserSerializer () {
		form = Form.getForm ();
	}
	
	/**
	 * Get the Form Object Reference
	 * 
	 * @return
	 */
	public Form getForm () {
		return form;
	}
	
	/*****************
	 * PARSER
	 *****************/
	
	/**
	 * parse
	 * 
	 * Begin the parsing process.
	 * 
	 * @param in
	 * @throws XmlPullParserException
	 * @throws IOException
	 */
	public void parse (InputStream in) throws XmlPullParserException, IOException {
		try {
			XmlPullParser parser = Xml.newPullParser();
			parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
			parser.setInput(in, null);
			parser.nextTag();
			readForm(parser);
		}
		finally {
			in.close();
		}
	}
	
	/**
	 * 
	 * @param parser
	 * @throws XmlPullParserException
	 * @throws IOException
	 */
	private void readForm (XmlPullParser parser) throws XmlPullParserException, IOException {
		parser.require(XmlPullParser.START_TAG, null, TITLE_TAG);

		while (parser.next() != XmlPullParser.END_TAG) {
			if (parser.getEventType() != XmlPullParser.START_TAG) {
				continue;
			}
			readStep (parser);
		}

		parser.require(XmlPullParser.END_TAG, null, TITLE_TAG);
	}
	
	/**
	 * 
	 * @param parser
	 * @throws XmlPullParserException
	 * @throws IOException
	 */
	private void readStep (XmlPullParser parser) throws XmlPullParserException, IOException {
		parser.require(XmlPullParser.START_TAG, null, STEP_TAG);

		FormStep step = new FormStep ();
		String num_str = parser.getAttributeValue(null, NUMBER_ATTR);
		int step_num = Integer.valueOf(num_str);
		step.setNumber(step_num);
		
		while (parser.next() != XmlPullParser.END_TAG) {
			if (parser.getEventType() != XmlPullParser.START_TAG) {
				continue;
			}
			
			String tag_name = parser.getName ();
			if (tag_name.equalsIgnoreCase(STANCE_TAG)) {
				readStance (parser, step);
			}
			else if (tag_name.equalsIgnoreCase(DIRECTION_TAG)) {
				readDirection (parser, step);
			}
			else if (tag_name.equalsIgnoreCase(HAND_TECH_TAG)) {
				readHandTechnique (parser, step);
			}
			else if (tag_name.equalsIgnoreCase(FOOT_TECH_TAG)) {
				readFootTechnique (parser, step);
			}
		}

		parser.require(XmlPullParser.END_TAG, null, TITLE_TAG);
		form.addFormStep(step);
	}

	/**
	 * 
	 * @param parser
	 * @param step
	 * @throws XmlPullParserException
	 * @throws IOException
	 */
	private void readFootTechnique(XmlPullParser parser, FormStep step) 
		throws XmlPullParserException, IOException {
		parser.require(XmlPullParser.START_TAG, null, FOOT_TECH_TAG);
		
		while (parser.next() != XmlPullParser.END_TAG) {
			if (parser.getEventType() != XmlPullParser.START_TAG) {
				continue;
			}
			parser.next ();
			String tech_str = parser.getText ();
			step.setFootTechnique (tech_str);
		}

		parser.require(XmlPullParser.END_TAG, null, FOOT_TECH_TAG);
	}

	/**
	 * 
	 * @param parser
	 * @param step
	 * @throws XmlPullParserException
	 * @throws IOException
	 */
	private void readHandTechnique(XmlPullParser parser, FormStep step) 
		throws XmlPullParserException, IOException {
		parser.require(XmlPullParser.START_TAG, null, HAND_TECH_TAG);

		while (parser.next() != XmlPullParser.END_TAG) {
			if (parser.getEventType() != XmlPullParser.START_TAG) {
				continue;
			}
			parser.next ();
			String tech_str = parser.getText ();
			step.setHandTechnique (tech_str);
		}

		parser.require(XmlPullParser.END_TAG, null, HAND_TECH_TAG);
	}

	/**
	 * 
	 * @param parser
	 * @param step
	 * @throws XmlPullParserException
	 * @throws IOException
	 */
	private void readDirection(XmlPullParser parser, FormStep step) 
		throws XmlPullParserException, IOException {
		parser.require(XmlPullParser.START_TAG, null, DIRECTION_TAG);

		while (parser.next() != XmlPullParser.END_TAG) {
			if (parser.getEventType() != XmlPullParser.START_TAG) {
				continue;
			}
			parser.next ();
			String dir_str = parser.getText ();
			step.setDirection (dir_str);
		}

		parser.require(XmlPullParser.END_TAG, null, DIRECTION_TAG);
	}

	/**
	 * 
	 * @param parser
	 * @param step
	 * @throws XmlPullParserException
	 * @throws IOException
	 */
	private void readStance(XmlPullParser parser, FormStep step) 
		throws XmlPullParserException, IOException {
		parser.require(XmlPullParser.START_TAG, null, STANCE_TAG);

		while (parser.next() != XmlPullParser.END_TAG) {
			if (parser.getEventType() != XmlPullParser.START_TAG) {
				continue;
			}
			parser.next ();
			String stance = parser.getText ();
			step.setStance (stance);
		}

		parser.require(XmlPullParser.END_TAG, null, STANCE_TAG);
	}

	/*****************
	 * SERIALIZER
	 *****************/
	
	/**********************************************************************************************
	 * 
	 * @param out
	 * @throws IOException
	 *********************************************************************************************/
	public void generateFormFile (OutputStream out) throws IOException {
		try {
			XmlSerializer serializer = Xml.newSerializer ();
			serializer.setOutput (out, null);
			serializer.startDocument (null, Boolean.TRUE);
			serializer.startTag (null, TITLE_TAG);
			writeForm (serializer);
			serializer.endTag (null, TITLE_TAG);
			serializer.endDocument ();
			serializer.flush ();
		}
		finally {
			out.close ();
		}
	}
	
	/**********************************************************************************************
	 * 
	 * @param serializer
	 * @throws IllegalArgumentException
	 * @throws IllegalStateException
	 * @throws IOException
	 *********************************************************************************************/
	private void writeForm (XmlSerializer serializer) 
		throws IllegalArgumentException, IllegalStateException, IOException {
				
		for (Iterator<FormStep> iter = form.iterator (); iter.hasNext (); ) {
			FormStep step = iter.next ();
			serializer.startTag (null, STEP_TAG);
			serializer.attribute (null, NUMBER_ATTR, Integer.toString (step.getNumber ()));
			writeStep (serializer, step);
			serializer.endTag (null, STEP_TAG);
		}
	}
	
	/**********************************************************************************************
	 * 
	 * @param serializer
	 * @param entry
	 * @throws IOException 
	 * @throws IllegalStateException 
	 * @throws IllegalArgumentException 
	 *********************************************************************************************/
	private void writeStep (XmlSerializer serializer, FormStep entry) 
		throws IllegalArgumentException, IllegalStateException, IOException {
		
		serializer.startTag (null, STANCE_TAG);
		serializer.text (entry.getStance ());
		serializer.endTag (null, STANCE_TAG);
		
		serializer.startTag (null, DIRECTION_TAG);
		serializer.text (entry.getDirectionStr ());
		serializer.endTag (null, DIRECTION_TAG);
		
		serializer.startTag (null, HAND_TECH_TAG);
		serializer.text (entry.getHandTechnique ());
		serializer.endTag (null, HAND_TECH_TAG);
		
		serializer.startTag (null, FOOT_TECH_TAG);
		serializer.text (entry.getFootTechnique ());
		serializer.endTag (null, FOOT_TECH_TAG);
	}
}
