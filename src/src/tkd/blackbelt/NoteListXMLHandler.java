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
 * 
 * @author jacoberg2
 *
 */
public class NoteListXMLHandler {
	
	private NoteList note_list;
	private static final String TITLE_TAG = "notes";
	private static final String ENTRY_TAG = "entry";
	private static final String DATE_ATTR = "date";
	
	/**
	 * 
	 */
	public NoteListXMLHandler () {
		note_list = NoteList.getNoteList ();
	}
	
	/**
	 * 
	 * @return
	 */
	public NoteList getNoteList () {
		return note_list;
	}
	
	/************
	 * PARSER
	 ************/
	
	/**
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
			readNoteList (parser);
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
	private void readNoteList (XmlPullParser parser) throws XmlPullParserException, IOException {

		parser.require(XmlPullParser.START_TAG, null, TITLE_TAG);

		while (parser.next() != XmlPullParser.END_TAG) {
			if (parser.getEventType() != XmlPullParser.START_TAG) {
				continue;
			}
			
			readNote (parser);
		}

		parser.require(XmlPullParser.END_TAG, null, TITLE_TAG);
	}
	
	/**
	 * 
	 * @param parser
	 * @throws XmlPullParserException
	 * @throws IOException
	 */
	private void readNote (XmlPullParser parser) throws XmlPullParserException, IOException {
		parser.require(XmlPullParser.START_TAG, null, ENTRY_TAG);

		while (parser.next() != XmlPullParser.END_TAG) {
			if (parser.getEventType() != XmlPullParser.START_TAG) {
				continue;
			}
			
			String date = parser.getAttributeValue(null, DATE_ATTR);
			String text = parser.getText ();
			
			note_list.addNote(new Date (date), text);
		}

		parser.require(XmlPullParser.END_TAG, null, ENTRY_TAG);
	}
	
	/*****************
	 * SERIALIZER
	 *****************/
	
	/**
	 * 
	 * @param out
	 * @throws IOException
	 */
	public void generateNoteListFile (OutputStream out) throws IOException {
		try {
			XmlSerializer serializer = Xml.newSerializer();
			serializer.setOutput(out, null);
			serializer.startDocument(null, Boolean.TRUE);
			serializer.startTag(null, TITLE_TAG);
			writeNoteList (serializer);
			serializer.endTag(null, TITLE_TAG);
			serializer.endDocument();
			serializer.flush();
		}
		finally {
			out.close();
		}
	}
	
	/**
	 * 
	 * @param serializer
	 * @throws IllegalArgumentException
	 * @throws IllegalStateException
	 * @throws IOException
	 */
	private void writeNoteList (XmlSerializer serializer) 
	throws IllegalArgumentException, IllegalStateException, IOException {

		for (Iterator<Note> iter = note_list.iterator (); iter.hasNext ();) {
			Note n = iter.next ();
			serializer.startTag (null, ENTRY_TAG);
			serializer.attribute (null, DATE_ATTR, n.getDate ().toString ());
			serializer.text (n.getNote ());
			serializer.endTag (null, ENTRY_TAG);
		}
	}
}
