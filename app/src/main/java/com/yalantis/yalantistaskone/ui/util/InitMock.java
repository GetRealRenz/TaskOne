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
                    if (name.equals("task")) {
                        mModel = new DataModel();
                    } else if (mModel != null) {
                        switch (name) {
                            case "title":
                                mModel.setTitle(parser.nextText());
                                break;
                            case "likes":
                                mModel.setLikes(parser.nextText());
                                break;
                            case "address":
                                mModel.setAddress(parser.nextText());
                                break;
                            case "date":
                                mModel.setDate(parser.nextText());
                                break;
                            case "daysleft":
                                mModel.setDaysleft(parser.nextText());
                                break;
                            case "description":
                                mModel.setDescription(parser.nextText());
                                break;
                            case "responsible":
                                mModel.setResponsible(parser.nextText());
                                break;
                            case "status":
                                mModel.setStatus(parser.nextText());
                                break;
                            case "registred":
                                mModel.setRegistred(parser.nextText());
                                break;
                            case "imgid": {
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
                    if (name.equalsIgnoreCase("task") && mModel != null && mModel.getStatus().equals("Виконано")) {
                        mDone.add(mModel);
                    } else if (name.equalsIgnoreCase("task") && mModel != null && mModel.getStatus().equals("В роботі")) {
                        mInWork.add(mModel);
                    } else if (name.equalsIgnoreCase("task") && mModel != null && mModel.getStatus().equals("Не виконано")) {
                        mUndone.add(mModel);
                    }
                }
            }
            event = parser.next();
        }
    }

    public List<DataModel> getModels(int status) {
        switch (status) {
            case 1:
                return mInWork;

            case 2:
                return mUndone;
            case 3:
                return mDone;
            default:
                return null;
        }
    }


}
