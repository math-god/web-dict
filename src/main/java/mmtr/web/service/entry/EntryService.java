package mmtr.web.service.entry;

import java.util.HashMap;
import java.util.List;

public interface EntryService {
    HashMap<String, HashMap<String, List<String>>> getDataInTableFormat(String type);
}
