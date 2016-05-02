package com.yalantis.yalantistaskone.ui.util;

import android.content.res.Resources;
import android.support.v4.content.ContextCompat;

import com.yalantis.yalantistaskone.ui.App;
import com.yalantis.yalantistaskone.ui.model.DataModel;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Антон on 17.04.2016.
 */
public class InitMock {
    /**
     * All this class is a mock, it will be newer used while working with real data(rest,http)
     */
    private List<DataModel> mDone;
    private List<DataModel> mInWork;
    private List<DataModel> mUndone;
    private DataModel mModel;

    public InitMock() {
        mDone = new ArrayList<>();
        mInWork = new ArrayList<>();
        mUndone = new ArrayList<>();
    }

    public void initModel(InputStream in) {
        try {
            XmlPullParserFactory pullParserFactory = XmlPullParserFactory.newInstance();
            XmlPullParser parser = pullParserFactory.newPullParser();
            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
            parser.setInput(in, null);
            parseXML(parser);
        } catch (XmlPullParserException | IOException e) {
            e.printStackTrace();

        }

    }

    /**
     * Parsing mock XML from assets
     */
    private void parseXML(XmlPullParser parser) throws XmlPullParserException, IOException {
        int event = parser.getEventType();
        while (event != XmlPullParser.END_DOCUMENT) {
            String name;
            switch (event) {
                case XmlPullParser.START_DOCUMENT: {
                    break;
                }
                case XmlPullParser.START_TAG: {
                    name = parser.getName();
                    if (name.equals(Constants.TASK)) {
                        mModel = new DataModel();
                    } else if (mModel != null) {
                        switch (name) {
                            case Constants.TITLE:
                                mModel.setTitle(parser.nextText());
                                break;
                            case Constants.LIKES:
                                mModel.setLikes(parser.nextText());
                                break;
                            case Constants.ADDRESS:
                                mModel.setAddress(parser.nextText());
                                break;
                            case Constants.DATE:
                                mModel.setDate(parser.nextText());
                                break;
                            case Constants.DEADLINE:
                                mModel.setDaysleft(parser.nextText());
                                break;
                            case Constants.DESCRIPTION:
                                mModel.setDescription(parser.nextText());
                                break;
                            case Constants.RESPONSIBLE:
                                mModel.setResponsible(parser.nextText());
                                break;
                            case Constants.STATUS:
                                mModel.setStatus(parser.nextText());
                                break;
                            case Constants.REGISTRED:
                                mModel.setRegistred(parser.nextText());
                                break;
                            case Constants.IMG_ID: {
                                Resources resources = App.getContext().getResources();
                                int id = resources.getIdentifier(parser.nextText(), "drawable", App.getContext().getPackageName());
                                mModel.setCategory(ContextCompat.getDrawable(App.getContext(), id));
                                break;
                            }
                        }
                    }
                    break;
                }
                case XmlPullParser.END_TAG: {
                    name = parser.getName();
                    if (name.equalsIgnoreCase(Constants.TASK) && mModel != null && mModel.getStatus().equals(Constants.DONE)) {
                        mDone.add(mModel);
                    } else if (name.equalsIgnoreCase(Constants.TASK) && mModel != null && mModel.getStatus().equals(Constants.IN_WORK)) {
                        mInWork.add(mModel);
                    } else if (name.equalsIgnoreCase(Constants.TASK) && mModel != null && mModel.getStatus().equals(Constants.UNDONE)) {
                        mUndone.add(mModel);
                    }
                }
            }
            event = parser.next();
        }
    }

    /**
     * Picking right data basing on status of task
     */
    public List<DataModel> getModels(int status) {
        switch (status) {
            case Constants.STATUS_INWORK:
                return mInWork;

            case Constants.STATUS_UNDONE:
                return mUndone;
            case Constants.STATUS_DONE:
                return mDone;
            default:
                return null;
        }
    }


}
