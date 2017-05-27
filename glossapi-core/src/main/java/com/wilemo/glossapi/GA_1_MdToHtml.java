package com.wilemo.glossapi;

import com.vladsch.flexmark.ast.Node;
import com.vladsch.flexmark.ext.ins.InsExtension;
import com.vladsch.flexmark.ext.tables.TablesExtension;
import com.vladsch.flexmark.ext.toc.SimTocExtension;
import com.vladsch.flexmark.html.HtmlRenderer;
import com.vladsch.flexmark.parser.Parser;
import com.vladsch.flexmark.util.options.MutableDataHolder;
import com.vladsch.flexmark.util.options.MutableDataSet;

import java.util.Arrays;

/**
 * Md to Html
 * Created by qiaoming on 2017/5/25.
 */
public class GA_1_MdToHtml {

    private static final String HTML_START = "" +
            "<!DOCTYPE html>\n" +
            "<html lang=\"zh\">\n" +
            "<head>\n" +
            "<meta charset=\"UTF-8\">\n" +
            "<!--[if IE]><meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\"><![endif]-->\n" +
            "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
            "<meta name=\"generator\" content=\"GlossApi 0.0.1\">\n" +
            "<title>Swagger Petstore</title>\n" +
            "<style>\n" +
            "</style>\n" +
            "</head>\n" +
            "<body>";

    private static final String HTML_END = "\n</body>\n</html>";

    private static final MutableDataHolder OPTIONS = new MutableDataSet()
            .set(Parser.EXTENSIONS, Arrays.asList(
                    InsExtension.create(),
                    TablesExtension.create(),
                    SimTocExtension.create()
            ));

    private static final Parser PARSER = Parser.builder(OPTIONS).build();
    private static final HtmlRenderer RENDERER = HtmlRenderer.builder(OPTIONS).build();

    public static void main(String[] args) {
        Node document = PARSER.parse(Utils.readDefaultString());
        String html = RENDERER.render(document);
        html = HTML_START + html + HTML_END;
        Utils.writeStringToResource("api.html", html);
    }
}
