package com.interlink.view;

/**
 * Created by employee on 7/13/16.
 */
public class Tag {
    private String openedTag;
    private String closedTag;
    public static final Tag EMPTY_TAG = new Tag("", "");

    public Tag(String openedTag, String closedTag) {
        this.openedTag = openedTag;
        this.closedTag = closedTag;
    }

    public String getOpenedTag() {
        return openedTag;
    }

    public void setOpenedTag(String openedTag) {
        this.openedTag = openedTag;
    }

    public String getClosedTag() {
        return closedTag;
    }

    public void setClosedTag(String closedTag) {
        this.closedTag = closedTag;
    }
}
