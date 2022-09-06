package org.telegram.ui.DialogBuilder;

import android.content.DialogInterface;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class DialogTemplate {
    public String title;
    public List<ViewTemplate> viewTemplates = new ArrayList<>();
    public DialogType type;
    public Consumer<List<View>> positiveListener;
    public DialogInterface.OnClickListener negativeListener;

    public void addEditTemplate(String text, String name, boolean singleLine) {
        addEditTemplate(text, name, singleLine,true, null);
    }
    public void addEditTemplate(String text, String name, boolean enabled, boolean singleLine) {
        addEditTemplate(text, name, singleLine, enabled, null);
    }

    public void addEditTemplate(String text, String name, boolean singleLine, boolean enabled, View.OnFocusChangeListener onClickListener) {
        EditTemplate editTemplate = new EditTemplate();
        editTemplate.text = text;
        editTemplate.name = name;
        editTemplate.singleLine = singleLine;
        editTemplate.onClickListener = onClickListener;
        editTemplate.enabled = enabled;
        viewTemplates.add(editTemplate);
    }

    public void addPhoneEditTemplate(String text, String name, boolean singleLine ) {
        addEditTemplate(text, name, singleLine,true,null);
    }

    public void addNumberEditTemplate(String text, String name, boolean singleLine) {
        NumberEditTemplate editTemplate = new NumberEditTemplate();
        editTemplate.text = text;
        editTemplate.name = name;
        editTemplate.singleLine = singleLine;
        viewTemplates.add(editTemplate);
    }

    public void addCheckboxTemplate(boolean checked, String name) {
        addCheckboxTemplate(checked, name, true);
    }

    public void addCheckboxTemplate(boolean checked, String name, boolean enabled) {
        addCheckboxTemplate(checked, name, enabled, null);
    }

    public void addCheckboxTemplate(boolean checked, String name, DialogCheckBox.OnCheckedChangeListener onCheckedChangeListener) {
        addCheckboxTemplate(checked, name, true, onCheckedChangeListener);
    }

    public void addCheckboxTemplate(boolean checked, String name, boolean enabled, DialogCheckBox.OnCheckedChangeListener onCheckedChangeListener) {
        CheckBoxTemplate checkBoxTemplate = new CheckBoxTemplate();
        checkBoxTemplate.name = name;
        checkBoxTemplate.checked = checked;
        checkBoxTemplate.enabled = enabled;
        checkBoxTemplate.onCheckedChangeListener = onCheckedChangeListener;
        viewTemplates.add(checkBoxTemplate);
    }
}
