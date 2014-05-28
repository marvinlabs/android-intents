package com.marvinlabs.intents.demo;

import android.content.Intent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DemoItem {

    public String description;
    public Intent intent;

    public DemoItem(String description, Intent intent) {
        this.description = description;
        this.intent = intent;
    }

    @Override
    public String toString() {
        return description;
    }
}