package com.coderti.brasilnews;

/**
 * Created by bruno on 14/03/2016.
 */
import java.util.List;

public interface FeedParser {
    List<Message> parse();
}