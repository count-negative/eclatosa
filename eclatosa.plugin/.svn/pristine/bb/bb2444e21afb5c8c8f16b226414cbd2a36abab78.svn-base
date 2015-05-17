/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package autosave.preferences;

import org.eclipse.jface.preference.BooleanFieldEditor;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

import autosave.AutoSaveActivator;

/**
 * This class represents a preference page that is contributed to the Preferences dialog. By subclassing <samp>FieldEditorPreferencePage</samp>, we can use the
 * field support built into JFace that allows us to create a page that is small and knows how to save, restore and apply itself.
 * <p>
 * This page is used to modify preferences only. They are stored in the preference store that belongs to the main plug-in class. That way, preferences can be
 * accessed directly via the preference store.
 */
public class AutoSavePreferencePage extends FieldEditorPreferencePage implements IWorkbenchPreferencePage {
	public static final String ENABLED = "enabled";

	public AutoSavePreferencePage() {
		super(GRID);
		setPreferenceStore(AutoSaveActivator.getDefault().getPreferenceStore());
		setDescription("Save editors on window deactivation");
	}

	/**
	 * Creates the field editors. Field editors are abstractions of the common GUI blocks needed to manipulate various types of preferences. Each field editor
	 * knows how to save and restore itself.
	 */
	@Override
	public void createFieldEditors() {
		addField(new BooleanFieldEditor(ENABLED, "&AutoSave activated", getFieldEditorParent()));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.IWorkbenchPreferencePage#init(org.eclipse.ui.IWorkbench)
	 */
	public void init(final IWorkbench workbench) {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.preference.FieldEditorPreferencePage#performOk()
	 */
	@Override
	public boolean performOk() {
		boolean ok = super.performOk();
		AutoSaveActivator.getDefault().registerListener();
		return ok;
	}
}