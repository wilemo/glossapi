import com.vladsch.flexmark.ast.Node;
import com.vladsch.flexmark.ext.jekyll.tag.JekyllTagExtension;
import com.vladsch.flexmark.ext.tables.TablesExtension;
import com.vladsch.flexmark.html.HtmlRenderer;
import com.vladsch.flexmark.parser.Parser;
import com.vladsch.flexmark.util.options.MutableDataHolder;
import com.vladsch.flexmark.util.options.MutableDataSet;
import com.wilemo.glossapi.Utils;

import java.util.Arrays;

public class Mark_2_Parse {

    private static final MutableDataHolder OPTIONS = new MutableDataSet()
            .set(JekyllTagExtension.LIST_INCLUDES_ONLY, false)
            .set(Parser.EXTENSIONS, Arrays.asList(
                    TablesExtension.create(),
                    JekyllTagExtension.create(),
                    HtmlStyleExtension.create()
            ));

    private static final Parser PARSER = Parser.builder(OPTIONS).build();
    private static final HtmlRenderer RENDERER = HtmlRenderer.builder(OPTIONS).build();

    public static void main(String[] args) {
        Node document = PARSER.parse(Utils.readResourceToString("res/test.md"));
        String html = RENDERER.render(document);
        System.out.println(html);
        Utils.writeStringToResource("/api.html", html);
    }
}
