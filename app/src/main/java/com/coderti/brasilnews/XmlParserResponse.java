package com.coderti.brasilnews;

import java.util.List;

/**
 * Created by bruno.santo on 14/03/2016.
 */
public interface XmlParserResponse {
    void processFinish(List<Message> output);
}
