package com.wilemo.glossapi;

import com.openhtmltopdf.DOMBuilder;
import com.openhtmltopdf.bidi.support.ICUBidiReorderer;
import com.openhtmltopdf.bidi.support.ICUBidiSplitter;
import com.openhtmltopdf.pdfboxout.PdfBoxOutputDevice;
import com.openhtmltopdf.pdfboxout.PdfBoxRenderer;
import com.openhtmltopdf.pdfboxout.PdfRendererBuilder;
import com.vladsch.flexmark.ast.Node;
import com.vladsch.flexmark.ext.tables.TablesExtension;
import com.vladsch.flexmark.ext.toc.SimTocExtension;
import com.vladsch.flexmark.html.HtmlRenderer;
import com.vladsch.flexmark.parser.Parser;
import com.vladsch.flexmark.pdf.converter.PdfConverterExtension;
import com.vladsch.flexmark.profiles.pegdown.Extensions;
import com.vladsch.flexmark.profiles.pegdown.PegdownOptionsAdapter;
import com.vladsch.flexmark.util.options.MutableDataHolder;
import com.vladsch.flexmark.util.options.MutableDataSet;
import org.jsoup.Jsoup;
import org.w3c.dom.Document;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;

/**
 * Md to Pdf
 * Created by qiaoming on 2017/5/25.
 */
public class GA_2_MdToPdf {

    private static final String HTML_START = "" +
            "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
            "<!DOCTYPE html\n" +
            "    PUBLIC \"-//W3C//DTD XHTML 1.0 Strict//EN\"\n" +
            "    \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd\">\n" +
            "<html xmlns=\"http://www.w3.org/1999/xhtml\" xml:lang=\"en\" lang=\"en\">\n" +
            "<head>\n" +
            "<bookmarks>\n" +
            " <bookmark name=\"Font Support\" href=\"#11version-information\"/>\n" +
            " <bookmark name=\"RTL &amp; BIDI Text Support\" href=\"#12-contact-information\">\n" +
                 " <bookmark name=\"RTL &amp; BIDI Text Support\" href=\"#12-contact-information\"/>\n" +
            " </bookmark>\n" +
            "</bookmarks>\n"+
            "<meta charset=\"UTF-8\" />\n" +
            "<!--[if IE]><meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\"><![endif]-->\n" +
            "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\"/>\n" +
            "<meta name=\"generator\" content=\"GlossApi 0.0.1\"/>\n" +
            "<title>Swagger Petstore</title>\n" +
            "<style>\n" +
            "/* Asciidoctor default stylesheet | MIT License | http://asciidoctor.org */\n" +
            "/* Remove comment around @import statement below when using as a custom stylesheet */\n" +
            "/*@import \"https://fonts.googleapis.com/css?family=Open+Sans:300,300italic,400,400italic,600,600italic%7CNoto+Serif:400,400italic,700,700italic%7CDroid+Sans+Mono:400,700\";*/\n" +
            "article,aside,details,figcaption,figure,footer,header,hgroup,main,nav,section,summary{display:block}\n" +
            "audio,canvas,video{display:inline-block}\n" +
            "audio:not([controls]){display:none;height:0}\n" +
            "[hidden],template{display:none}\n" +
            "script{display:none!important}\n" +
            "html{font-family:sans-serif;-ms-text-size-adjust:100%;-webkit-text-size-adjust:100%}\n" +
            "body{margin:0}\n" +
            "a{background:transparent}\n" +
            "a:focus{outline:thin dotted}\n" +
            "a:active,a:hover{outline:0}\n" +
            "h1{font-size:2em;margin:.67em 0}\n" +
            "abbr[title]{border-bottom:1px dotted}\n" +
            "b,strong{font-weight:bold}\n" +
            "dfn{font-style:italic}\n" +
            "hr{-moz-box-sizing:content-box;box-sizing:content-box;height:0}\n" +
            "mark{background:#ff0;color:#000}\n" +
            "code,kbd,pre,samp{font-family:monospace;font-size:1em}\n" +
            "pre{white-space:pre-wrap}\n" +
            "q{quotes:\"\\201C\" \"\\201D\" \"\\2018\" \"\\2019\"}\n" +
            "small{font-size:80%}\n" +
            "sub,sup{font-size:75%;line-height:0;position:relative;vertical-align:baseline}\n" +
            "sup{top:-.5em}\n" +
            "sub{bottom:-.25em}\n" +
            "img{border:0}\n" +
            "svg:not(:root){overflow:hidden}\n" +
            "figure{margin:0}\n" +
            "fieldset{border:1px solid silver;margin:0 2px;padding:.35em .625em .75em}\n" +
            "legend{border:0;padding:0}\n" +
            "button,input,select,textarea{font-family:inherit;font-size:100%;margin:0}\n" +
            "button,input{line-height:normal}\n" +
            "button,select{text-transform:none}\n" +
            "button,html input[type=\"button\"],input[type=\"reset\"],input[type=\"submit\"]{-webkit-appearance:button;cursor:pointer}\n" +
            "button[disabled],html input[disabled]{cursor:default}\n" +
            "input[type=\"checkbox\"],input[type=\"radio\"]{box-sizing:border-box;padding:0}\n" +
            "input[type=\"search\"]{-webkit-appearance:textfield;-moz-box-sizing:content-box;-webkit-box-sizing:content-box;box-sizing:content-box}\n" +
            "input[type=\"search\"]::-webkit-search-cancel-button,input[type=\"search\"]::-webkit-search-decoration{-webkit-appearance:none}\n" +
            "button::-moz-focus-inner,input::-moz-focus-inner{border:0;padding:0}\n" +
            "textarea{overflow:auto;vertical-align:top}\n" +
            "table{border-collapse:collapse;border-spacing:0}\n" +
            "*,*:before,*:after{-moz-box-sizing:border-box;-webkit-box-sizing:border-box;box-sizing:border-box}\n" +
            "html,body{font-size:100%}\n" +
            "body{background:#fff;color:rgba(0,0,0,.8);padding:0;margin:0;font-family:\"Noto Serif\",\"DejaVu Serif\",serif;font-weight:400;font-style:normal;line-height:1;position:relative;cursor:auto}\n" +
            "a:hover{cursor:pointer}\n" +
            "img,object,embed{max-width:100%;height:auto}\n" +
            "object,embed{height:100%}\n" +
            "img{-ms-interpolation-mode:bicubic}\n" +
            ".left{float:left!important}\n" +
            ".right{float:right!important}\n" +
            ".text-left{text-align:left!important}\n" +
            ".text-right{text-align:right!important}\n" +
            ".text-center{text-align:center!important}\n" +
            ".text-justify{text-align:justify!important}\n" +
            ".hide{display:none}\n" +
            "body{-webkit-font-smoothing:antialiased}\n" +
            "img,object,svg{display:inline-block;vertical-align:middle}\n" +
            "textarea{height:auto;min-height:50px}\n" +
            "select{width:100%}\n" +
            ".center{margin-left:auto;margin-right:auto}\n" +
            ".spread{width:100%}\n" +
            "p.lead,.paragraph.lead>p,#preamble>.sectionbody>.paragraph:first-of-type p{font-size:1.21875em;line-height:1.6}\n" +
            ".subheader,.admonitionblock td.content>.title,.audioblock>.title,.exampleblock>.title,.imageblock>.title,.listingblock>.title,.literalblock>.title,.stemblock>.title,.openblock>.title,.paragraph>.title,.quoteblock>.title,table.tableblock>.title,.verseblock>.title,.videoblock>.title,.dlist>.title,.olist>.title,.ulist>.title,.qlist>.title,.hdlist>.title{line-height:1.45;color:#7a2518;font-weight:400;margin-top:0;margin-bottom:.25em}\n" +
            "div,dl,dt,dd,ul,ol,li,h1,h2,h3,#toctitle,.sidebarblock>.content>.title,h4,h5,h6,pre,form,p,blockquote,th,td{margin:0;padding:0;direction:ltr}\n" +
            "a{color:#2156a5;text-decoration:underline;line-height:inherit}\n" +
            "a:hover,a:focus{color:#1d4b8f}\n" +
            "a img{border:none}\n" +
            "p{font-family:inherit;font-weight:400;font-size:1em;line-height:1.6;margin-bottom:1.25em;text-rendering:optimizeLegibility}\n" +
            "p aside{font-size:.875em;line-height:1.35;font-style:italic}\n" +
            "h1,h2,h3,#toctitle,.sidebarblock>.content>.title,h4,h5,h6{font-family:\"Open Sans\",\"DejaVu Sans\",sans-serif;font-weight:300;font-style:normal;color:#ba3925;text-rendering:optimizeLegibility;margin-top:1em;margin-bottom:.5em;line-height:1.0125em}\n" +
            "h1 small,h2 small,h3 small,#toctitle small,.sidebarblock>.content>.title small,h4 small,h5 small,h6 small{font-size:60%;color:#e99b8f;line-height:0}\n" +
            "h1{font-size:2.125em}\n" +
            "h2{font-size:1.6875em}\n" +
            "h3,#toctitle,.sidebarblock>.content>.title{font-size:1.375em}\n" +
            "h4,h5{font-size:1.125em}\n" +
            "h6{font-size:1em}\n" +
            "hr{border:solid #ddddd8;border-width:1px 0 0;clear:both;margin:1.25em 0 1.1875em;height:0}\n" +
            "em,i{font-style:italic;line-height:inherit}\n" +
            "strong,b{font-weight:bold;line-height:inherit}\n" +
            "small{font-size:60%;line-height:inherit}\n" +
            "code{font-family:\"Droid Sans Mono\",\"DejaVu Sans Mono\",monospace;font-weight:400;color:rgba(0,0,0,.9)}\n" +
            "ul,ol,dl{font-size:1em;line-height:1.6;margin-bottom:1.25em;list-style-position:outside;font-family:inherit}\n" +
            "ul,ol,ul.no-bullet,ol.no-bullet{margin-left:1.5em}\n" +
            "ul li ul,ul li ol{margin-left:1.25em;margin-bottom:0;font-size:1em}\n" +
            "ul.square li ul,ul.circle li ul,ul.disc li ul{list-style:inherit}\n" +
            "ul.square{list-style-type:square}\n" +
            "ul.circle{list-style-type:circle}\n" +
            "ul.disc{list-style-type:disc}\n" +
            "ul.no-bullet{list-style:none}\n" +
            "ol li ul,ol li ol{margin-left:1.25em;margin-bottom:0}\n" +
            "dl dt{margin-bottom:.3125em;font-weight:bold}\n" +
            "dl dd{margin-bottom:1.25em}\n" +
            "abbr,acronym{text-transform:uppercase;font-size:90%;color:rgba(0,0,0,.8);border-bottom:1px dotted #ddd;cursor:help}\n" +
            "abbr{text-transform:none}\n" +
            "blockquote{margin:0 0 1.25em;padding:.5625em 1.25em 0 1.1875em;border-left:1px solid #ddd}\n" +
            "blockquote cite{display:block;font-size:.9375em;color:rgba(0,0,0,.6)}\n" +
            "blockquote cite:before{content:\"\\2014 \\0020\"}\n" +
            "blockquote cite a,blockquote cite a:visited{color:rgba(0,0,0,.6)}\n" +
            "blockquote,blockquote p{line-height:1.6;color:rgba(0,0,0,.85)}\n" +
            "@media only screen and (min-width:768px){h1,h2,h3,#toctitle,.sidebarblock>.content>.title,h4,h5,h6{line-height:1.2}\n" +
            "    h1{font-size:2.75em}\n" +
            "    h2{font-size:2.3125em}\n" +
            "    h3,#toctitle,.sidebarblock>.content>.title{font-size:1.6875em}\n" +
            "    h4{font-size:1.4375em}}\n" +
            "table{background:#fff;margin-bottom:1.25em;border:solid 1px #dedede}\n" +
            "table thead,table tfoot{background:#f7f8f7;font-weight:bold}\n" +
            "table thead tr th,table thead tr td,table tfoot tr th,table tfoot tr td{padding:.5em .625em .625em;font-size:inherit;color:rgba(0,0,0,.8);text-align:left}\n" +
            "table tr th,table tr td{padding:.5625em .625em;font-size:inherit;color:rgba(0,0,0,.8)}\n" +
            "table tr.even,table tr.alt,table tr:nth-of-type(even){background:#f8f8f7}\n" +
            "table thead tr th,table tfoot tr th,table tbody tr td,table tr td,table tfoot tr td{display:table-cell;line-height:1.6}\n" +
            "body{tab-size:4}\n" +
            "h1,h2,h3,#toctitle,.sidebarblock>.content>.title,h4,h5,h6{line-height:1.2;word-spacing:-.05em}\n" +
            "h1 strong,h2 strong,h3 strong,#toctitle strong,.sidebarblock>.content>.title strong,h4 strong,h5 strong,h6 strong{font-weight:400}\n" +
            ".clearfix:before,.clearfix:after,.float-group:before,.float-group:after{content:\" \";display:table}\n" +
            ".clearfix:after,.float-group:after{clear:both}\n" +
            "*:not(pre)>code{font-size:.9375em;font-style:normal!important;letter-spacing:0;padding:.1em .5ex;word-spacing:-.15em;background-color:#f7f7f8;-webkit-border-radius:4px;border-radius:4px;line-height:1.45;text-rendering:optimizeSpeed}\n" +
            "pre,pre>code{line-height:1.45;color:rgba(0,0,0,.9);font-family:\"Droid Sans Mono\",\"DejaVu Sans Mono\",monospace;font-weight:400;text-rendering:optimizeSpeed}\n" +
            ".keyseq{color:rgba(51,51,51,.8)}\n" +
            "kbd{font-family:\"Droid Sans Mono\",\"DejaVu Sans Mono\",monospace;display:inline-block;color:rgba(0,0,0,.8);font-size:.65em;line-height:1.45;background-color:#f7f7f7;border:1px solid #ccc;-webkit-border-radius:3px;border-radius:3px;-webkit-box-shadow:0 1px 0 rgba(0,0,0,.2),0 0 0 .1em white inset;box-shadow:0 1px 0 rgba(0,0,0,.2),0 0 0 .1em #fff inset;margin:0 .15em;padding:.2em .5em;vertical-align:middle;position:relative;top:-.1em;white-space:nowrap}\n" +
            ".keyseq kbd:first-child{margin-left:0}\n" +
            ".keyseq kbd:last-child{margin-right:0}\n" +
            ".menuseq,.menu{color:rgba(0,0,0,.8)}\n" +
            "b.button:before,b.button:after{position:relative;top:-1px;font-weight:400}\n" +
            "b.button:before{content:\"[\";padding:0 3px 0 2px}\n" +
            "b.button:after{content:\"]\";padding:0 2px 0 3px}\n" +
            "p a>code:hover{color:rgba(0,0,0,.9)}\n" +
            "#header,#content,#footnotes,#footer{width:100%;margin-left:auto;margin-right:auto;margin-top:0;margin-bottom:0;max-width:62.5em;*zoom:1;position:relative;padding-left:.9375em;padding-right:.9375em}\n" +
            "#header:before,#header:after,#content:before,#content:after,#footnotes:before,#footnotes:after,#footer:before,#footer:after{content:\" \";display:table}\n" +
            "#header:after,#content:after,#footnotes:after,#footer:after{clear:both}\n" +
            "#content{margin-top:1.25em}\n" +
            "#content:before{content:none}\n" +
            "#header>h1:first-child{color:rgba(0,0,0,.85);margin-top:2.25rem;margin-bottom:0}\n" +
            "#header>h1:first-child+#toc{margin-top:8px;border-top:1px solid #ddddd8}\n" +
            "#header>h1:only-child,body.toc2 #header>h1:nth-last-child(2){border-bottom:1px solid #ddddd8;padding-bottom:8px}\n" +
            "#header .details{border-bottom:1px solid #ddddd8;line-height:1.45;padding-top:.25em;padding-bottom:.25em;padding-left:.25em;color:rgba(0,0,0,.6);display:-ms-flexbox;display:-webkit-flex;display:flex;-ms-flex-flow:row wrap;-webkit-flex-flow:row wrap;flex-flow:row wrap}\n" +
            "#header .details span:first-child{margin-left:-.125em}\n" +
            "#header .details span.email a{color:rgba(0,0,0,.85)}\n" +
            "#header .details br{display:none}\n" +
            "#header .details br+span:before{content:\"\\00a0\\2013\\00a0\"}\n" +
            "#header .details br+span.author:before{content:\"\\00a0\\22c5\\00a0\";color:rgba(0,0,0,.85)}\n" +
            "#header .details br+span#revremark:before{content:\"\\00a0|\\00a0\"}\n" +
            "#header #revnumber{text-transform:capitalize}\n" +
            "#header #revnumber:after{content:\"\\00a0\"}\n" +
            "#content>h1:first-child:not([class]){color:rgba(0,0,0,.85);border-bottom:1px solid #ddddd8;padding-bottom:8px;margin-top:0;padding-top:1rem;margin-bottom:1.25rem}\n" +
            "#toc{border-bottom:1px solid #efefed;padding-bottom:.5em}\n" +
            "#toc>ul{margin-left:.125em}\n" +
            "#toc ul.sectlevel0>li>a{font-style:italic}\n" +
            "#toc ul.sectlevel0 ul.sectlevel1{margin:.5em 0}\n" +
            "#toc ul{font-family:\"Open Sans\",\"DejaVu Sans\",sans-serif;list-style-type:none}\n" +
            "#toc li{line-height:1.3334;margin-top:.3334em}\n" +
            "#toc a{text-decoration:none}\n" +
            "#toc a:active{text-decoration:underline}\n" +
            "#toctitle{color:#7a2518;font-size:1.2em}\n" +
            "@media only screen and (min-width:768px){#toctitle{font-size:1.375em}\n" +
            "    body.toc2{padding-left:15em;padding-right:0}\n" +
            "    #toc.toc2{margin-top:0!important;background-color:#f8f8f7;position:fixed;width:15em;left:0;top:0;border-right:1px solid #efefed;border-top-width:0!important;border-bottom-width:0!important;z-index:1000;padding:1.25em 1em;height:100%;overflow:auto}\n" +
            "    #toc.toc2 #toctitle{margin-top:0;margin-bottom:.8rem;font-size:1.2em}\n" +
            "    #toc.toc2>ul{font-size:.9em;margin-bottom:0}\n" +
            "    #toc.toc2 ul ul{margin-left:0;padding-left:1em}\n" +
            "    #toc.toc2 ul.sectlevel0 ul.sectlevel1{padding-left:0;margin-top:.5em;margin-bottom:.5em}\n" +
            "    body.toc2.toc-right{padding-left:0;padding-right:15em}\n" +
            "    body.toc2.toc-right #toc.toc2{border-right-width:0;border-left:1px solid #efefed;left:auto;right:0}}\n" +
            "@media only screen and (min-width:1280px){body.toc2{padding-left:20em;padding-right:0}\n" +
            "    #toc.toc2{width:20em}\n" +
            "    #toc.toc2 #toctitle{font-size:1.375em}\n" +
            "    #toc.toc2>ul{font-size:.95em}\n" +
            "    #toc.toc2 ul ul{padding-left:1.25em}\n" +
            "    body.toc2.toc-right{padding-left:0;padding-right:20em}}\n" +
            "#content #toc{border-style:solid;border-width:1px;border-color:#e0e0dc;margin-bottom:1.25em;padding:1.25em;background:#f8f8f7;-webkit-border-radius:4px;border-radius:4px}\n" +
            "#content #toc>:first-child{margin-top:0}\n" +
            "#content #toc>:last-child{margin-bottom:0}\n" +
            "#footer{max-width:100%;background-color:rgba(0,0,0,.8);padding:1.25em}\n" +
            "#footer-text{color:rgba(255,255,255,.8);line-height:1.44}\n" +
            ".sect1{padding-bottom:.625em}\n" +
            "@media only screen and (min-width:768px){.sect1{padding-bottom:1.25em}}\n" +
            ".sect1+.sect1{border-top:1px solid #efefed}\n" +
            "#content h1>a.anchor,h2>a.anchor,h3>a.anchor,#toctitle>a.anchor,.sidebarblock>.content>.title>a.anchor,h4>a.anchor,h5>a.anchor,h6>a.anchor{position:absolute;z-index:1001;width:1.5ex;margin-left:-1.5ex;display:block;text-decoration:none!important;visibility:hidden;text-align:center;font-weight:400}\n" +
            "#content h1>a.anchor:before,h2>a.anchor:before,h3>a.anchor:before,#toctitle>a.anchor:before,.sidebarblock>.content>.title>a.anchor:before,h4>a.anchor:before,h5>a.anchor:before,h6>a.anchor:before{content:\"\\00A7\";font-size:.85em;display:block;padding-top:.1em}\n" +
            "#content h1:hover>a.anchor,#content h1>a.anchor:hover,h2:hover>a.anchor,h2>a.anchor:hover,h3:hover>a.anchor,#toctitle:hover>a.anchor,.sidebarblock>.content>.title:hover>a.anchor,h3>a.anchor:hover,#toctitle>a.anchor:hover,.sidebarblock>.content>.title>a.anchor:hover,h4:hover>a.anchor,h4>a.anchor:hover,h5:hover>a.anchor,h5>a.anchor:hover,h6:hover>a.anchor,h6>a.anchor:hover{visibility:visible}\n" +
            "#content h1>a.link,h2>a.link,h3>a.link,#toctitle>a.link,.sidebarblock>.content>.title>a.link,h4>a.link,h5>a.link,h6>a.link{color:#ba3925;text-decoration:none}\n" +
            "#content h1>a.link:hover,h2>a.link:hover,h3>a.link:hover,#toctitle>a.link:hover,.sidebarblock>.content>.title>a.link:hover,h4>a.link:hover,h5>a.link:hover,h6>a.link:hover{color:#a53221}\n" +
            ".audioblock,.imageblock,.literalblock,.listingblock,.stemblock,.videoblock{margin-bottom:1.25em}\n" +
            ".admonitionblock td.content>.title,.audioblock>.title,.exampleblock>.title,.imageblock>.title,.listingblock>.title,.literalblock>.title,.stemblock>.title,.openblock>.title,.paragraph>.title,.quoteblock>.title,table.tableblock>.title,.verseblock>.title,.videoblock>.title,.dlist>.title,.olist>.title,.ulist>.title,.qlist>.title,.hdlist>.title{text-rendering:optimizeLegibility;text-align:left;font-family:\"Noto Serif\",\"DejaVu Serif\",serif;font-size:1rem;font-style:italic}\n" +
            "table.tableblock>caption.title{white-space:nowrap;overflow:visible;max-width:0}\n" +
            ".paragraph.lead>p,#preamble>.sectionbody>.paragraph:first-of-type p{color:rgba(0,0,0,.85)}\n" +
            "table.tableblock #preamble>.sectionbody>.paragraph:first-of-type p{font-size:inherit}\n" +
            ".admonitionblock>table{border-collapse:separate;border:0;background:none;width:100%}\n" +
            ".admonitionblock>table td.icon{text-align:center;width:80px}\n" +
            ".admonitionblock>table td.icon img{max-width:none}\n" +
            ".admonitionblock>table td.icon .title{font-weight:bold;font-family:\"Open Sans\",\"DejaVu Sans\",sans-serif;text-transform:uppercase}\n" +
            ".admonitionblock>table td.content{padding-left:1.125em;padding-right:1.25em;border-left:1px solid #ddddd8;color:rgba(0,0,0,.6)}\n" +
            ".admonitionblock>table td.content>:last-child>:last-child{margin-bottom:0}\n" +
            ".exampleblock>.content{border-style:solid;border-width:1px;border-color:#e6e6e6;margin-bottom:1.25em;padding:1.25em;background:#fff;-webkit-border-radius:4px;border-radius:4px}\n" +
            ".exampleblock>.content>:first-child{margin-top:0}\n" +
            ".exampleblock>.content>:last-child{margin-bottom:0}\n" +
            ".sidebarblock{border-style:solid;border-width:1px;border-color:#e0e0dc;margin-bottom:1.25em;padding:1.25em;background:#f8f8f7;-webkit-border-radius:4px;border-radius:4px}\n" +
            ".sidebarblock>:first-child{margin-top:0}\n" +
            ".sidebarblock>:last-child{margin-bottom:0}\n" +
            ".sidebarblock>.content>.title{color:#7a2518;margin-top:0;text-align:center}\n" +
            ".exampleblock>.content>:last-child>:last-child,.exampleblock>.content .olist>ol>li:last-child>:last-child,.exampleblock>.content .ulist>ul>li:last-child>:last-child,.exampleblock>.content .qlist>ol>li:last-child>:last-child,.sidebarblock>.content>:last-child>:last-child,.sidebarblock>.content .olist>ol>li:last-child>:last-child,.sidebarblock>.content .ulist>ul>li:last-child>:last-child,.sidebarblock>.content .qlist>ol>li:last-child>:last-child{margin-bottom:0}\n" +
            ".literalblock pre,.listingblock pre:not(.highlight),.listingblock pre[class=\"highlight\"],.listingblock pre[class^=\"highlight \"],.listingblock pre.CodeRay,.listingblock pre.prettyprint{background:#f7f7f8}\n" +
            ".sidebarblock .literalblock pre,.sidebarblock .listingblock pre:not(.highlight),.sidebarblock .listingblock pre[class=\"highlight\"],.sidebarblock .listingblock pre[class^=\"highlight \"],.sidebarblock .listingblock pre.CodeRay,.sidebarblock .listingblock pre.prettyprint{background:#f2f1f1}\n" +
            ".literalblock pre,.literalblock pre[class],.listingblock pre,.listingblock pre[class]{-webkit-border-radius:4px;border-radius:4px;word-wrap:break-word;padding:1em;font-size:.8125em}\n" +
            ".literalblock pre.nowrap,.literalblock pre[class].nowrap,.listingblock pre.nowrap,.listingblock pre[class].nowrap{overflow-x:auto;white-space:pre;word-wrap:normal}\n" +
            "@media only screen and (min-width:768px){.literalblock pre,.literalblock pre[class],.listingblock pre,.listingblock pre[class]{font-size:.90625em}}\n" +
            "@media only screen and (min-width:1280px){.literalblock pre,.literalblock pre[class],.listingblock pre,.listingblock pre[class]{font-size:1em}}\n" +
            ".literalblock.output pre{color:#f7f7f8;background-color:rgba(0,0,0,.9)}\n" +
            ".listingblock pre.highlightjs{padding:0}\n" +
            ".listingblock pre.highlightjs>code{padding:1em;-webkit-border-radius:4px;border-radius:4px}\n" +
            ".listingblock pre.prettyprint{border-width:0}\n" +
            ".listingblock>.content{position:relative}\n" +
            ".listingblock code[data-lang]:before{display:none;content:attr(data-lang);position:absolute;font-size:.75em;top:.425rem;right:.5rem;line-height:1;text-transform:uppercase;color:#999}\n" +
            ".listingblock:hover code[data-lang]:before{display:block}\n" +
            ".listingblock.terminal pre .command:before{content:attr(data-prompt);padding-right:.5em;color:#999}\n" +
            ".listingblock.terminal pre .command:not([data-prompt]):before{content:\"$\"}\n" +
            "table.pyhltable{border-collapse:separate;border:0;margin-bottom:0;background:none}\n" +
            "table.pyhltable td{vertical-align:top;padding-top:0;padding-bottom:0;line-height:1.45}\n" +
            "table.pyhltable td.code{padding-left:.75em;padding-right:0}\n" +
            "pre.pygments .lineno,table.pyhltable td:not(.code){color:#999;padding-left:0;padding-right:.5em;border-right:1px solid #ddddd8}\n" +
            "pre.pygments .lineno{display:inline-block;margin-right:.25em}\n" +
            "table.pyhltable .linenodiv{background:none!important;padding-right:0!important}\n" +
            ".quoteblock{margin:0 1em 1.25em 1.5em;display:table}\n" +
            ".quoteblock>.title{margin-left:-1.5em;margin-bottom:.75em}\n" +
            ".quoteblock blockquote,.quoteblock blockquote p{color:rgba(0,0,0,.85);font-size:1.15rem;line-height:1.75;word-spacing:.1em;letter-spacing:0;font-style:italic;text-align:justify}\n" +
            ".quoteblock blockquote{margin:0;padding:0;border:0}\n" +
            ".quoteblock blockquote:before{content:\"\\201c\";float:left;font-size:2.75em;font-weight:bold;line-height:.6em;margin-left:-.6em;color:#7a2518;text-shadow:0 1px 2px rgba(0,0,0,.1)}\n" +
            ".quoteblock blockquote>.paragraph:last-child p{margin-bottom:0}\n" +
            ".quoteblock .attribution{margin-top:.5em;margin-right:.5ex;text-align:right}\n" +
            ".quoteblock .quoteblock{margin-left:0;margin-right:0;padding:.5em 0;border-left:3px solid rgba(0,0,0,.6)}\n" +
            ".quoteblock .quoteblock blockquote{padding:0 0 0 .75em}\n" +
            ".quoteblock .quoteblock blockquote:before{display:none}\n" +
            ".verseblock{margin:0 1em 1.25em 1em}\n" +
            ".verseblock pre{font-family:\"Open Sans\",\"DejaVu Sans\",sans;font-size:1.15rem;color:rgba(0,0,0,.85);font-weight:300;text-rendering:optimizeLegibility}\n" +
            ".verseblock pre strong{font-weight:400}\n" +
            ".verseblock .attribution{margin-top:1.25rem;margin-left:.5ex}\n" +
            ".quoteblock .attribution,.verseblock .attribution{font-size:.9375em;line-height:1.45;font-style:italic}\n" +
            ".quoteblock .attribution br,.verseblock .attribution br{display:none}\n" +
            ".quoteblock .attribution cite,.verseblock .attribution cite{display:block;letter-spacing:-.025em;color:rgba(0,0,0,.6)}\n" +
            ".quoteblock.abstract{margin:0 0 1.25em 0;display:block}\n" +
            ".quoteblock.abstract blockquote,.quoteblock.abstract blockquote p{text-align:left;word-spacing:0}\n" +
            ".quoteblock.abstract blockquote:before,.quoteblock.abstract blockquote p:first-of-type:before{display:none}\n" +
            "table.tableblock{max-width:100%;border-collapse:separate}\n" +
            "table.tableblock td>.paragraph:last-child p>p:last-child,table.tableblock th>p:last-child,table.tableblock td>p:last-child{margin-bottom:0}\n" +
            "table.tableblock,th.tableblock,td.tableblock{border:0 solid #dedede}\n" +
            "table.grid-all th.tableblock,table.grid-all td.tableblock{border-width:0 1px 1px 0}\n" +
            "table.grid-all tfoot>tr>th.tableblock,table.grid-all tfoot>tr>td.tableblock{border-width:1px 1px 0 0}\n" +
            "table.grid-cols th.tableblock,table.grid-cols td.tableblock{border-width:0 1px 0 0}\n" +
            "table.grid-all *>tr>.tableblock:last-child,table.grid-cols *>tr>.tableblock:last-child{border-right-width:0}\n" +
            "table.grid-rows th.tableblock,table.grid-rows td.tableblock{border-width:0 0 1px 0}\n" +
            "table.grid-all tbody>tr:last-child>th.tableblock,table.grid-all tbody>tr:last-child>td.tableblock,table.grid-all thead:last-child>tr>th.tableblock,table.grid-rows tbody>tr:last-child>th.tableblock,table.grid-rows tbody>tr:last-child>td.tableblock,table.grid-rows thead:last-child>tr>th.tableblock{border-bottom-width:0}\n" +
            "table.grid-rows tfoot>tr>th.tableblock,table.grid-rows tfoot>tr>td.tableblock{border-width:1px 0 0 0}\n" +
            "table.frame-all{border-width:1px}\n" +
            "table.frame-sides{border-width:0 1px}\n" +
            "table.frame-topbot{border-width:1px 0}\n" +
            "th.halign-left,td.halign-left{text-align:left}\n" +
            "th.halign-right,td.halign-right{text-align:right}\n" +
            "th.halign-center,td.halign-center{text-align:center}\n" +
            "th.valign-top,td.valign-top{vertical-align:top}\n" +
            "th.valign-bottom,td.valign-bottom{vertical-align:bottom}\n" +
            "th.valign-middle,td.valign-middle{vertical-align:middle}\n" +
            "table thead th,table tfoot th{font-weight:bold}\n" +
            "tbody tr th{display:table-cell;line-height:1.6;background:#f7f8f7}\n" +
            "tbody tr th,tbody tr th p,tfoot tr th,tfoot tr th p{color:rgba(0,0,0,.8);font-weight:bold}\n" +
            "p.tableblock>code:only-child{background:none;padding:0}\n" +
            "p.tableblock{font-size:1em}\n" +
            "td>div.verse{white-space:pre}\n" +
            "ol{margin-left:1.75em}\n" +
            "ul li ol{margin-left:1.5em}\n" +
            "dl dd{margin-left:1.125em}\n" +
            "dl dd:last-child,dl dd:last-child>:last-child{margin-bottom:0}\n" +
            "ol>li p,ul>li p,ul dd,ol dd,.olist .olist,.ulist .ulist,.ulist .olist,.olist .ulist{margin-bottom:.625em}\n" +
            "ul.unstyled,ol.unnumbered,ul.checklist,ul.none{list-style-type:none}\n" +
            "ul.unstyled,ol.unnumbered,ul.checklist{margin-left:.625em}\n" +
            "ul.checklist li>p:first-child>.fa-square-o:first-child,ul.checklist li>p:first-child>.fa-check-square-o:first-child{width:1em;font-size:.85em}\n" +
            "ul.checklist li>p:first-child>input[type=\"checkbox\"]:first-child{width:1em;position:relative;top:1px}\n" +
            "ul.inline{margin:0 auto .625em auto;margin-left:-1.375em;margin-right:0;padding:0;list-style:none;overflow:hidden}\n" +
            "ul.inline>li{list-style:none;float:left;margin-left:1.375em;display:block}\n" +
            "ul.inline>li>*{display:block}\n" +
            ".unstyled dl dt{font-weight:400;font-style:normal}\n" +
            "ol.arabic{list-style-type:decimal}\n" +
            "ol.decimal{list-style-type:decimal-leading-zero}\n" +
            "ol.loweralpha{list-style-type:lower-alpha}\n" +
            "ol.upperalpha{list-style-type:upper-alpha}\n" +
            "ol.lowerroman{list-style-type:lower-roman}\n" +
            "ol.upperroman{list-style-type:upper-roman}\n" +
            "ol.lowergreek{list-style-type:lower-greek}\n" +
            ".hdlist>table,.colist>table{border:0;background:none}\n" +
            ".hdlist>table>tbody>tr,.colist>table>tbody>tr{background:none}\n" +
            "td.hdlist1,td.hdlist2{vertical-align:top;padding:0 .625em}\n" +
            "td.hdlist1{font-weight:bold;padding-bottom:1.25em}\n" +
            ".literalblock+.colist,.listingblock+.colist{margin-top:-.5em}\n" +
            ".colist>table tr>td:first-of-type{padding:0 .75em;line-height:1}\n" +
            ".colist>table tr>td:last-of-type{padding:.25em 0}\n" +
            ".thumb,.th{line-height:0;display:inline-block;border:solid 4px #fff;-webkit-box-shadow:0 0 0 1px #ddd;box-shadow:0 0 0 1px #ddd}\n" +
            ".imageblock.left,.imageblock[style*=\"float: left\"]{margin:.25em .625em 1.25em 0}\n" +
            ".imageblock.right,.imageblock[style*=\"float: right\"]{margin:.25em 0 1.25em .625em}\n" +
            ".imageblock>.title{margin-bottom:0}\n" +
            ".imageblock.thumb,.imageblock.th{border-width:6px}\n" +
            ".imageblock.thumb>.title,.imageblock.th>.title{padding:0 .125em}\n" +
            ".image.left,.image.right{margin-top:.25em;margin-bottom:.25em;display:inline-block;line-height:0}\n" +
            ".image.left{margin-right:.625em}\n" +
            ".image.right{margin-left:.625em}\n" +
            "a.image{text-decoration:none;display:inline-block}\n" +
            "a.image object{pointer-events:none}\n" +
            "sup.footnote,sup.footnoteref{font-size:.875em;position:static;vertical-align:super}\n" +
            "sup.footnote a,sup.footnoteref a{text-decoration:none}\n" +
            "sup.footnote a:active,sup.footnoteref a:active{text-decoration:underline}\n" +
            "#footnotes{padding-top:.75em;padding-bottom:.75em;margin-bottom:.625em}\n" +
            "#footnotes hr{width:20%;min-width:6.25em;margin:-.25em 0 .75em 0;border-width:1px 0 0 0}\n" +
            "#footnotes .footnote{padding:0 .375em 0 .225em;line-height:1.3334;font-size:.875em;margin-left:1.2em;text-indent:-1.05em;margin-bottom:.2em}\n" +
            "#footnotes .footnote a:first-of-type{font-weight:bold;text-decoration:none}\n" +
            "#footnotes .footnote:last-of-type{margin-bottom:0}\n" +
            "#content #footnotes{margin-top:-.625em;margin-bottom:0;padding:.75em 0}\n" +
            ".gist .file-data>table{border:0;background:#fff;width:100%;margin-bottom:0}\n" +
            ".gist .file-data>table td.line-data{width:99%}\n" +
            "div.unbreakable{page-break-inside:avoid}\n" +
            ".big{font-size:larger}\n" +
            ".small{font-size:smaller}\n" +
            ".underline{text-decoration:underline}\n" +
            ".overline{text-decoration:overline}\n" +
            ".line-through{text-decoration:line-through}\n" +
            ".aqua{color:#00bfbf}\n" +
            ".aqua-background{background-color:#00fafa}\n" +
            ".black{color:#000}\n" +
            ".black-background{background-color:#000}\n" +
            ".blue{color:#0000bf}\n" +
            ".blue-background{background-color:#0000fa}\n" +
            ".fuchsia{color:#bf00bf}\n" +
            ".fuchsia-background{background-color:#fa00fa}\n" +
            ".gray{color:#606060}\n" +
            ".gray-background{background-color:#7d7d7d}\n" +
            ".green{color:#006000}\n" +
            ".green-background{background-color:#007d00}\n" +
            ".lime{color:#00bf00}\n" +
            ".lime-background{background-color:#00fa00}\n" +
            ".maroon{color:#600000}\n" +
            ".maroon-background{background-color:#7d0000}\n" +
            ".navy{color:#000060}\n" +
            ".navy-background{background-color:#00007d}\n" +
            ".olive{color:#606000}\n" +
            ".olive-background{background-color:#7d7d00}\n" +
            ".purple{color:#600060}\n" +
            ".purple-background{background-color:#7d007d}\n" +
            ".red{color:#bf0000}\n" +
            ".red-background{background-color:#fa0000}\n" +
            ".silver{color:#909090}\n" +
            ".silver-background{background-color:#bcbcbc}\n" +
            ".teal{color:#006060}\n" +
            ".teal-background{background-color:#007d7d}\n" +
            ".white{color:#bfbfbf}\n" +
            ".white-background{background-color:#fafafa}\n" +
            ".yellow{color:#bfbf00}\n" +
            ".yellow-background{background-color:#fafa00}\n" +
            "span.icon>.fa{cursor:default}\n" +
            ".admonitionblock td.icon [class^=\"fa icon-\"]{font-size:2.5em;text-shadow:1px 1px 2px rgba(0,0,0,.5);cursor:default}\n" +
            ".admonitionblock td.icon .icon-note:before{content:\"\\f05a\";color:#19407c}\n" +
            ".admonitionblock td.icon .icon-tip:before{content:\"\\f0eb\";text-shadow:1px 1px 2px rgba(155,155,0,.8);color:#111}\n" +
            ".admonitionblock td.icon .icon-warning:before{content:\"\\f071\";color:#bf6900}\n" +
            ".admonitionblock td.icon .icon-caution:before{content:\"\\f06d\";color:#bf3400}\n" +
            ".admonitionblock td.icon .icon-important:before{content:\"\\f06a\";color:#bf0000}\n" +
            ".conum[data-value]{display:inline-block;color:#fff!important;background-color:rgba(0,0,0,.8);-webkit-border-radius:100px;border-radius:100px;text-align:center;font-size:.75em;width:1.67em;height:1.67em;line-height:1.67em;font-family:\"Open Sans\",\"DejaVu Sans\",sans-serif;font-style:normal;font-weight:bold}\n" +
            ".conum[data-value] *{color:#fff!important}\n" +
            ".conum[data-value]+b{display:none}\n" +
            ".conum[data-value]:after{content:attr(data-value)}\n" +
            "pre .conum[data-value]{position:relative;top:-.125em}\n" +
            "b.conum *{color:inherit!important}\n" +
            ".conum:not([data-value]):empty{display:none}\n" +
            "dt,th.tableblock,td.content,div.footnote{text-rendering:optimizeLegibility}\n" +
            "h1,h2,p,td.content,span.alt{letter-spacing:-.01em}\n" +
            "p strong,td.content strong,div.footnote strong{letter-spacing:-.005em}\n" +
            "p,blockquote,dt,td.content,span.alt{font-size:1.0625rem}\n" +
            "p{margin-bottom:1.25rem}\n" +
            ".sidebarblock p,.sidebarblock dt,.sidebarblock td.content,p.tableblock{font-size:1em}\n" +
            ".exampleblock>.content{background-color:#fffef7;border-color:#e0e0dc;-webkit-box-shadow:0 1px 4px #e0e0dc;box-shadow:0 1px 4px #e0e0dc}\n" +
            ".print-only{display:none!important}\n" +
            "@media print{@page{margin:1.25cm .75cm}\n" +
            "    *{-webkit-box-shadow:none!important;box-shadow:none!important;text-shadow:none!important}\n" +
            "    a{color:inherit!important;text-decoration:underline!important}\n" +
            "    a.bare,a[href^=\"#\"],a[href^=\"mailto:\"]{text-decoration:none!important}\n" +
            "    a[href^=\"http:\"]:not(.bare):after,a[href^=\"https:\"]:not(.bare):after{content:\"(\" attr(href) \")\";display:inline-block;font-size:.875em;padding-left:.25em}\n" +
            "    abbr[title]:after{content:\" (\" attr(title) \")\"}\n" +
            "    pre,blockquote,tr,img,object,svg{page-break-inside:avoid}\n" +
            "    thead{display:table-header-group}\n" +
            "    svg{max-width:100%}\n" +
            "    p,blockquote,dt,td.content{font-size:1em;orphans:3;widows:3}\n" +
            "    h2,h3,#toctitle,.sidebarblock>.content>.title{page-break-after:avoid}\n" +
            "    #toc,.sidebarblock,.exampleblock>.content{background:none!important}\n" +
            "    #toc{border-bottom:1px solid #ddddd8!important;padding-bottom:0!important}\n" +
            "    .sect1{padding-bottom:0!important}\n" +
            "    .sect1+.sect1{border:0!important}\n" +
            "    #header>h1:first-child{margin-top:1.25rem}\n" +
            "    body.book #header{text-align:center}\n" +
            "    body.book #header>h1:first-child{border:0!important;margin:2.5em 0 1em 0}\n" +
            "    body.book #header .details{border:0!important;display:block;padding:0!important}\n" +
            "    body.book #header .details span:first-child{margin-left:0!important}\n" +
            "    body.book #header .details br{display:block}\n" +
            "    body.book #header .details br+span:before{content:none!important}\n" +
            "    body.book #toc{border:0!important;text-align:left!important;padding:0!important;margin:0!important}\n" +
            "    body.book #toc,body.book #preamble,body.book h1.sect0,body.book .sect1>h2{page-break-before:always}\n" +
            "    .listingblock code[data-lang]:before{display:block}\n" +
            "    #footer{background:none!important;padding:0 .9375em}\n" +
            "    #footer-text{color:rgba(0,0,0,.6)!important;font-size:.9em}\n" +
            "    .hide-on-print{display:none!important}\n" +
            "    .print-only{display:block!important}\n" +
            "    .hide-for-print{display:none!important}\n" +
            "    .show-for-print{display:inherit!important}}" +
            "</style>\n" +
            "</head>\n" +
            "<body>";

    private static final String HTML_END = "\n</body>\n</html>";

    private static final MutableDataHolder OPTIONS = new MutableDataSet()
            .set(Parser.EXTENSIONS, Arrays.asList(
                    TablesExtension.create(),
                    SimTocExtension.create()
            ));

    private static final MutableDataHolder PDF_OPTIONS = PegdownOptionsAdapter.flexmarkOptions(
            Extensions.ALL & ~(Extensions.ANCHORLINKS | Extensions.EXTANCHORLINKS_WRAP)
    ).toMutable();

    private static final Parser PARSER = Parser.builder(OPTIONS).build();
    private static final HtmlRenderer RENDERER = HtmlRenderer.builder(OPTIONS).build();

    public static void main(String[] args) {
        Node document = PARSER.parse(Utils.readDefaultString());
        String html = RENDERER.render(document);
        html = HTML_START + html + HTML_END;
        exportToPdf(Utils.RESOURCE_PATH + "api.pdf", html,"", null);
    }

    public static void exportToPdf(final String out, final String html, final String url, final PdfRendererBuilder.TextDirection defaultTextDirection) {
        OutputStream os = null;
        try {
            os = new FileOutputStream(out);
            // There are more options on the builder than shown below.
            PdfRendererBuilder builder = new PdfRendererBuilder();
//            org.jsoup.nodes.Document doc;
//            doc = Jsoup.parse(html);
//            Document dom = DOMBuilder.jsoup2DOM(doc);

            builder.withHtmlContent(html, url);
            builder.toStream(os);
            builder.run();
        } catch (Exception e) {
            e.printStackTrace();
            // LOG exception
        } finally {
            try {
                os.close();
            } catch (IOException e) {
                // swallow
            }
        }
    }
}
