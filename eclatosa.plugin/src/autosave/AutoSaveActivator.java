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
package autosave;

import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.ui.IStartup;
import org.eclipse.ui.IWindowListener;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

import autosave.preferences.AutoSavePreferencePage;

/**
 * The activator class controls the plug-in life cycle
 */
public class AutoSaveActivator extends AbstractUIPlugin implements IStartup {
	// The plug-in ID
	public static final String PLUGIN_ID = "AutoSave";
	// The shared instance
	private static AutoSaveActivator plugin;

	/**
	 * The constructor
	 */
	public AutoSaveActivator() {
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext )
	 */
	@Override
	public void start(final BundleContext context) throws Exception {
		super.start(context);
		plugin = this;
		registerListener();
	}

	/**
	 *
	 */
	public void registerListener() {
		final IPreferenceStore store = AutoSaveActivator.getDefault().getPreferenceStore();
		boolean isEnabled = store.getBoolean(AutoSavePreferencePage.ENABLED);
		if (isEnabled) {
			getWorkbench().addWindowListener(new IWindowListener() {
				@Override
				public void windowActivated(final IWorkbenchWindow window) {
				}

				@Override
				public void windowClosed(final IWorkbenchWindow window) {
				}

				@Override
				public void windowDeactivated(final IWorkbenchWindow window) {
					boolean isEnabled = store.getBoolean(AutoSavePreferencePage.ENABLED);
					if (isEnabled) {
						PlatformUI.getWorkbench().saveAllEditors(false);
					}
				}

				@Override
				public void windowOpened(final IWorkbenchWindow window) {
				}
			});
		}
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext )
	 */
	@Override
	public void stop(final BundleContext context) throws Exception {
		plugin = null;
		super.stop(context);
	}

	/**
	 * Returns the shared instance
	 *
	 * @return the shared instance
	 */
	public static AutoSaveActivator getDefault() {
		return plugin;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.eclipse.ui.IStartup#earlyStartup()
	 */
	@Override
	public void earlyStartup() {
		// nothing to do
	}
}
