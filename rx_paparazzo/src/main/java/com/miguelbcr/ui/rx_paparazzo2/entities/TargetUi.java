/*
 * Copyright 2016 Miguel Garcia
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.miguelbcr.ui.rx_paparazzo2.entities;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

public class TargetUi {
  private Object ui;

  public TargetUi(Object ui) {
    this.ui = ui;
  }

  public FragmentActivity activity() {
    return fragment() != null ? fragment().getActivity() : (FragmentActivity) ui;
  }

  @Nullable
  public Fragment fragment() {
    if (ui instanceof Fragment) {
      return (Fragment) ui;
    }
    return null;
  }

  public Object ui() {
    return ui;
  }

  public void setUi(Object ui) {
    this.ui = ui;
  }

  public Context getContext() {
    return fragment() == null ? activity() : fragment().getContext();
  }
}
