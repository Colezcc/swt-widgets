/*******************************************************************************
 * Copyright (c) 2011 Laurent CARON.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *

 * Contributors:
 *     Laurent CARON (laurent.caron at gmail dot com) - Initial API and implementation
 *     Haixing Hu (starfish.hu at gmail dot com)  - Modification for personal use.
 *******************************************************************************/
package com.github.haixing_hu.swt.prompt;

import org.eclipse.swt.custom.StyledText;

/**
 * Focus/Control listener for a StyledText widget
 */
class StyledTextFocusControlListener extends BaseFocusControlListener {

  /**
   * Constructor
   *
   * @param control
   *          control on which this listener will be attached
   */
  public StyledTextFocusControlListener(final StyledText control) {
    super(control);
  }

  @Override
  protected void hidePrompt() {
    ((StyledText) control).setText(EMPTY_STRING);
  }

  @Override
  protected void highLightPrompt() {
    control.getDisplay().asyncExec(new Runnable() {
      @Override
      public void run() {
        ((StyledText) StyledTextFocusControlListener.this.control).selectAll();

      }
    });
  }

  @Override
  protected void fillPromptText() {
    final String promptText = PromptSupport.getPrompt(control);
    if (promptText != null) {
      ((StyledText) control).setText(promptText);
    }

  }

  @Override
  protected boolean isFilled() {
    final String promptText = PromptSupport.getPrompt(control);
    if ((promptText != null)
        && promptText.equals(((StyledText) control).getText().trim())) {
      return false;
    }
    return ! EMPTY_STRING.equals(((StyledText) control).getText().trim());
  }

}
