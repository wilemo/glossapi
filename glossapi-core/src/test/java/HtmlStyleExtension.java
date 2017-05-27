import com.vladsch.flexmark.ast.Heading;
import com.vladsch.flexmark.ast.Node;
import com.vladsch.flexmark.html.AttributeProvider;
import com.vladsch.flexmark.html.AttributeProviderFactory;
import com.vladsch.flexmark.html.HtmlRenderer;
import com.vladsch.flexmark.html.IndependentAttributeProviderFactory;
import com.vladsch.flexmark.html.renderer.AttributablePart;
import com.vladsch.flexmark.html.renderer.NodeRendererContext;
import com.vladsch.flexmark.util.html.Attributes;
import com.vladsch.flexmark.util.options.MutableDataHolder;

/**
 * Created by qiaoming on 2017/5/25.
 */
public class HtmlStyleExtension implements HtmlRenderer.HtmlRendererExtension {

    @Override
    public void rendererOptions(final MutableDataHolder options) {
        // add any configuration settings to options you want to apply to everything, here
    }

    @Override
    public void extend(final HtmlRenderer.Builder rendererBuilder, final String rendererType) {
        rendererBuilder.attributeProviderFactory(HtmlStyleExtension.SampleAttributeProvider.Factory());
    }

    static HtmlStyleExtension create() {
        return new HtmlStyleExtension();
    }

    static class SampleAttributeProvider implements AttributeProvider {
        @Override
        public void setAttributes(final Node node, final AttributablePart part, final Attributes attributes) {
            if (node instanceof Heading) {
                attributes.replaceValue("class", "my-autolink-class");
            }
        }

        static AttributeProviderFactory Factory() {
            return new IndependentAttributeProviderFactory() {
                @Override
                public AttributeProvider create(NodeRendererContext context) {
                    return new SampleAttributeProvider();
                }
            };
        }
    }
}

