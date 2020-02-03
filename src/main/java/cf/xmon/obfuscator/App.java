package cf.xmon.obfuscator;


import joptsimple.OptionException;
import joptsimple.OptionParser;
import joptsimple.OptionSet;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.*;
import java.util.logging.Logger;

        /**
         * @author Xmon
         * @since 03.02.2020
         */

public class App {

        static Logger logger = Logger.getLogger("main");
        private static HashMap<String, String> classy = new HashMap<>();
        private static String test = "";
        private static String to = "";
        private static List<String> emoji = new ArrayList<>(Arrays.asList("😄", "😃", "😀", "😊", "☺", "😉", "😍", "😘", "😚", "😗", "😙", "😜", "😝", "😛", "😳", "😁", "😔", "😌", "😒", "😞", "😣", "😢", "😂", "😭", "😪", "😥", "😰", "😅", "😓", "😩", "😫", "😨", "😱", "😠", "😡", "😤", "😖", "😆", "😋", "😷", "😎", "😴", "😵", "😲", "😟", "😦", "😧", "😈", "👿", "😮", "😬", "😐", "😕", "😯", "😶", "😇", "😏", "😑", "👲", "👳", "👮", "👷", "💂", "👶", "👦", "👧", "👨", "👩", "👴", "👵", "👱", "👼", "👸", "😺", "😸", "😻", "😽", "😼", "🙀", "😿", "😹", "😾", "👹", "👺", "🙈", "🙉", "🙊", "💀", "👽", "💩", "🔥", "✨", "🌟", "💫", "💥", "💢", "💦", "💧", "💤", "💨", "👂", "👀", "👃", "👅", "👄", "👍", "👎", "👌", "👊", "✊", "✌", "👋", "✋", "👐", "👆", "👇", "👉", "👈", "🙌", "🙏", "☝", "👏", "💪", "🚶", "🏃", "💃", "👫", "👪", "👬", "👭", "💏", "💑", "👯", "🙆", "🙅", "💁", "🙋", "💆", "💇", "💅", "👰", "🙎", "🙍", "🙇", "🎩", "👑", "👒", "👟", "👞", "👡", "👠", "👢", "👕", "👔", "👚", "👗", "🎽", "👖", "👘", "👙", "💼", "👜", "👝", "👛", "👓", "🎀", "🌂", "💄", "💛", "💙", "💜", "💚", "❤", "💔", "💗", "💓", "💕", "💖", "💞", "💘", "💌", "💋", "💍", "💎", "👤", "👥", "💬", "👣", "💭", "🐶", "🐺", "🐱", "🐭", "🐹", "🐰", "🐸", "🐯", "🐨", "🐻", "🐷", "🐽", "🐮", "🐗", "🐵", "🐒", "🐴", "🐑", "🐘", "🐼", "🐧", "🐦", "🐤", "🐥", "🐣", "🐔", "🐍", "🐢", "🐛", "🐝", "🐜", "🐞", "🐌", "🐙", "🐚", "🐠", "🐟", "🐬", "🐳", "🐋", "🐄", "🐏", "🐀", "🐃", "🐅", "🐇", "🐉", "🐎", "🐐", "🐓", "🐕", "🐖", "🐁", "🐂", "🐲", "🐡", "🐊", "🐫", "🐪", "🐆", "🐈", "🐩", "🐾", "💐", "🌸", "🌷", "🍀", "🌹", "🌻", "🌺", "🍁", "🍃", "🍂", "🌿", "🌾", "🍄", "🌵", "🌴", "🌲", "🌳", "🌰", "🌱", "🌼", "🌐", "🌞", "🌝", "🌚", "🌑", "🌒", "🌓", "🌔", "🌕", "🌖", "🌗", "🌘", "🌜", "🌛", "🌙", "🌍", "🌎", "🌏", "🌋", "🌌", "🌠", "⭐", "☀", "⛅", "☁", "⚡", "☔", "❄", "⛄", "🌀", "🌁", "🌈", "🌊", "🎍", "💝", "🎎", "🎒", "🎓", "🎏", "🎆", "🎇", "🎐", "🎑", "🎃", "👻", "🎅", "🎄", "🎁", "🎋", "🎉", "🎊", "🎈", "🎌", "🔮", "🎥", "📷", "📹", "📼", "💿", "📀", "💽", "💾", "💻", "📱", "☎", "📞", "📟", "📠", "📡", "📺", "📻", "🔊", "🔉", "🔈", "🔇", "🔔", "🔕", "📢", "📣", "⏳", "⌛", "⏰", "⌚", "🔓", "🔒", "🔏", "🔐", "🔑", "🔎", "💡", "🔦", "🔆", "🔅", "🔌", "🔋", "🔍", "🛁", "🛀", "🚿", "🚽", "🔧", "🔩", "🔨", "🚪", "🚬", "💣", "🔫", "🔪", "💊", "💉", "💰", "💴", "💵", "💷", "💶", "💳", "💸", "📲", "📧", "📥", "📤", "✉", "📩", "📨", "📯", "📫", "📪", "📬", "📭", "📮", "📦", "📝", "📄", "📃", "📑", "📊", "📈", "📉", "📜", "📋", "📅", "📆", "📇", "📁", "📂", "✂", "📌", "📎", "✒", "✏", "📏", "📐", "📕", "📗", "📘", "📙", "📓", "📔", "📒", "📚", "📖", "🔖", "📛", "🔬", "🔭", "📰", "🎨", "🎬", "🎤", "🎧", "🎼", "🎵", "🎶", "🎹", "🎻", "🎺", "🎷", "🎸", "👾", "🎮", "🃏", "🎴", "🀄", "🎲", "🎯", "🏈", "🏀", "⚽", "⚾", "🎾", "🎱", "🏉", "🎳", "⛳", "🚵", "🚴", "🏁", "🏇", "🏆", "🎿", "🏂", "🏊", "🏄", "🎣", "☕", "🍵", "🍶", "🍼", "🍺", "🍻", "🍸", "🍹", "🍷", "🍴", "🍕", "🍔", "🍟", "🍗", "🍖", "🍝", "🍛", "🍤", "🍱", "🍣", "🍥", "🍙", "🍘", "🍚", "🍜", "🍲", "🍢", "🍡", "🍳", "🍞", "🍩", "🍮", "🍦", "🍨", "🍧", "🎂", "🍰", "🍪", "🍫", "🍬", "🍭", "🍯", "🍎", "🍏", "🍊", "🍋", "🍒", "🍇", "🍉", "🍓", "🍑", "🍈", "🍌", "🍐", "🍍", "🍠", "🍆", "🍅", "🌽", "🏠", "🏡", "🏫", "🏢", "🏣", "🏥", "🏦", "🏪", "🏩", "🏨", "💒", "⛪", "🏬", "🏤", "🌇", "🌆", "🏯", "🏰", "⛺", "🏭", "🗼", "🗾", "🗻", "🌄", "🌅", "🌃", "🗽", "🌉", "🎠", "🎡", "⛲", "🎢", "🚢", "⛵", "🚤", "🚣", "⚓", "🚀", "✈", "💺", "🚁", "🚂", "🚊", "🚉", "🚞", "🚆", "🚄", "🚅", "🚈", "🚇", "🚝", "🚋", "🚃", "🚎", "🚌", "🚍", "🚙", "🚘", "🚗", "🚕", "🚖", "🚛", "🚚", "🚨", "🚓", "🚔", "🚒", "🚑", "🚐", "🚲", "🚡", "🚟", "🚠", "🚜", "💈", "🚏", "🎫", "🚦", "🚥", "⚠", "🚧", "🔰", "⛽", "🏮", "🎰", "♨", "🗿", "🎪", "🎭", "📍", "🚩", "⬆", "⬇", "⬅", "➡", "🔠", "🔡", "🔤", "↗", "↖", "↘", "↙", "↔", "↕", "🔄", "◀", "▶", "🔼", "🔽", "↩", "↪", "ℹ", "⏪", "⏩", "⏫", "⏬", "⤵", "⤴", "🆗", "🔀", "🔁", "🔂", "🆕", "🆙", "🆒", "🆓", "🆖", "📶", "🎦", "🈁", "🈯", "🈳", "🈵", "🈴", "🈲", "🉐", "🈹", "🈺", "🈶", "🈚", "🚻", "🚹", "🚺", "🚼", "🚾", "🚰", "🚮", "🅿", "♿", "🚭", "🈷", "🈸", "🈂", "Ⓜ", "🛂", "🛄", "🛅", "🛃", "🉑", "㊙", "㊗", "🆑", "🆘", "🆔", "🚫", "🔞", "📵", "🚯", "🚱", "🚳", "🚷", "🚸", "⛔", "✳", "❇", "❎", "✅", "✴", "💟", "🆚", "📳", "📴", "🅰", "🅱", "🆎", "🅾", "💠", "➿", "♻", "♈", "♉", "♊", "♋", "♌", "♍", "♎", "♏", "♐", "♑", "♒", "♓", "⛎", "🔯", "🏧", "💹", "💲", "💱", "©", "®", "™", "〽", "〰", "🔝", "🔚", "🔙", "🔛", "🔜", "❌", "⭕", "❗", "❓", "❕", "❔", "🔃", "🕛", "🕧", "🕐", "🕜", "🕑", "🕝", "🕒", "🕞", "🕓", "🕟", "🕔", "🕠", "🕕", "🕖", "🕗", "🕘", "🕙", "🕚", "🕡", "🕢", "🕣", "🕤", "🕥", "🕦", "✖", "➕", "➖", "➗", "♠", "♥", "♣", "♦", "💮", "💯", "✔", "☑", "🔘", "🔗", "➰", "🔱", "🔲", "🔳", "◼", "◻", "◾", "◽", "▪", "▫", "🔺", "⬜", "⬛", "⚫", "⚪", "🔴", "🔵", "🔻", "🔶", "🔷", "🔸", "🔹"));
        public static void main(String... args){
            long start = System.currentTimeMillis();
            //System.setProperty("Dfile.encoding", "UTF-8");
            System.setProperty("file.encoding", "UTF-8");
            System.setProperty("http.agent", "Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10.4; en-US; rv:1.9.2.2) Gecko/20100316 Firefox/3.6.2");
            logger.info("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
            logger.info("         x-Obfuscator 1.1v by Xmon         ");
            logger.info("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
            OptionParser parser = new OptionParser();
            parser.accepts("toobf").withRequiredArg().required();
            parser.accepts("css").withRequiredArg().required();
            parser.accepts("imgencode").withOptionalArg().ofType(boolean.class).defaultsTo(false);
            parser.accepts("help").forHelp();
            try{
                OptionSet options = parser.parse(args);
                if (options.has("help")){
                    logger.info("java -jar x-obfuscator.jar --toobf sample.html --css index.css --imgencode <true/false>");
                    System.exit(-1);
                }
                File toobf = new File((String) options.valueOf("toobf"));
                if (!toobf.exists()){
                    logger.warning(String.format("File %s not found!", toobf.getName()));
                    System.exit(-1);
                }
                logger.info(String.format("%s found!", toobf.getName()));
                File css = new File((String) options.valueOf("css"));
                if (!css.exists()){
                    logger.warning(String.format("File %s not found!", css.getName()));
                    System.exit(-1);
                }
                logger.info(String.format("%s found!", css.getName()));
                logger.info("Starting search of classes..");
                StringBuilder obf = new StringBuilder();
                obf.append("<!-- Obfuscator by Xmon (https://github.com/Xmonpl) Please leave information about the author! -->");
                Files.readAllLines(toobf.toPath()).forEach(x ->{
                    String a = StringUtils.substringBetween(x, "class=\"", "\"");
                    to = x;
                    if ((boolean) options.valueOf("imgencode")){
                        String srcimg = StringUtils.substringBetween(to, "src=\"", "\"");
                        if (srcimg != null) {
                            try {
                                File img = new File(srcimg);
                                if (img.exists()) {
                                    String mimetype = Files.probeContentType(img.toPath());
                                    if (mimetype != null && mimetype.split("/")[0].equals("image")) {
                                        byte[] fileContent = FileUtils.readFileToByteArray(img);
                                        String encodedString = Base64.getEncoder().encodeToString(fileContent);
                                        to = to.replace(srcimg, ("data:image/png;base64," + encodedString));
                                        logger.info("Encode one image!");
                                    }
                                }else{
                                    if (isValidURL(srcimg)){
                                        String encodedString = Base64.getEncoder().encodeToString(IOUtils.toByteArray(new URL(srcimg).openStream()));
                                        to = to.replace(srcimg, ("data:image/png;base64," + encodedString));
                                        logger.info("Encode one image!");
                                    }
                                }
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                    if (a != null) {
                        if (a.contains(" ")){
                            Arrays.stream(a.split(" ")).forEach(b ->{
                                if (classy.get(b) == null) {
                                    classy.put(b, randomString(b));
                                }
                                to = to.replace(b, classy.get(b));
                            });
                            obf.append(to);
                        }else{
                            if (classy.get(a) == null) {
                                classy.put(a, randomString(a));
                            }
                            obf.append(to.replace(a, classy.get(a)));
                        }
                    }else{
                        obf.append(to);
                    }
                });
                obf.append("<!-- Obfuscator by Xmon (https://github.com/Xmonpl) Please leave information about the author! -->");
                logger.info(String.format("%s classes found!", classy.size()));
                logger.info("Securing the css file...");
                StringBuilder obfcss = new StringBuilder();
                obfcss.append("/* Obfuscator by Xmon (https://github.com/Xmonpl) Please leave information about the author! */");
                Files.readAllLines(css.toPath()).forEach(obfcss::append);
                obfcss.append("/* Obfuscator by Xmon (https://github.com/Xmonpl) Please leave information about the author! */");
                test = obfcss.toString();
                classy.forEach((key, val) ->{
                    test = test.replace(key, val);
                });
                logger.info("Done.");
                long time = System.currentTimeMillis();
                Files.write(Paths.get(time + "-style.obf.css"), test.getBytes(), StandardOpenOption.CREATE);
                Files.write(Paths.get(time + "-index.obf.html"), obf.toString().replace((String) options.valueOf("css"), time + "-style.obf.css").getBytes(), StandardOpenOption.CREATE);
                logger.info(String.format("All operations took %s ms.", (System.currentTimeMillis() - start)));
            } catch (OptionException | IOException e) {
                logger.warning(String.format("Error - %s (java -jar x-obfuscator.jar --help) https://github.com/Xmonpl/x-PageObfuscator/issues/new", e.getMessage()));
            }
        }
        @NotNull
        private static String randomString(String s){
            String aa = "Il";
            StringBuilder sb = new StringBuilder(10);
            for (int i = 0; i < 10; i++) {
                int ii = (int)(aa.length() * Math.random());
                sb.append(aa.charAt(ii));
            }
            return "xmon-" + sb.toString() + "-" + emoji.get(new Random().nextInt(emoji.size()));
        }
        private static boolean isValidURL(String urlString) {
            try {
                URL url = new URL(urlString);
                url.toURI();
                return true;
            } catch (Exception exception) {
                return false;
            }
        }
}
