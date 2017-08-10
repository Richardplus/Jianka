package tech.jianka.data;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static tech.jianka.utils.ItemUtils.Obj2Bytes;
import static tech.jianka.utils.ItemUtils.getSDCardPath;
import static tech.jianka.utils.ItemUtils.getTaskItems;
import static tech.jianka.utils.ItemUtils.saveFileToSDCard;

/**
 * Created by Richard on 2017/8/3.
 */

public class TaskData {
    private String[] taskGroup = {"重要|紧急", "重要|不紧急", "不重要|紧急", "不重要|不紧急"};

    private String[] paths = {
            "jianka/task/很重要-很紧急", "jianka/task/很重要-不紧急",
            "jianka/task/不重要-很紧急", "jianka/task/不重要-不紧急"};
    private static List<Card> data = new ArrayList<>();

    public TaskData() {
        //taskGroup初始化
        int[] type = DataType.TASK_TYPE;
        for (int i = 0; i < 4; i++) {
            data.add(new Card(taskGroup[i], getSDCardPath(paths[i]), DataType.GROUP, type[i]));
        }

        //taskItems初始化
        for (String path : paths) {
            List<Card> cards = getTaskItems(getSDCardPath(path));
            if (cards != null) {
                data.addAll(cards);
            }
        }
    }

    public List<Card> getData() {
        return data;
    }

    public static boolean addTask(Card card) {
        String cardName = System.currentTimeMillis() + ".card";
        if (saveFileToSDCard(Obj2Bytes(card), card.getFilePath(), cardName)) {
            card.setFilePath(card.getFilePath() + File.separator +cardName );
            data.add(4, card);
            return true;
        }
        return false;
    }

    public static boolean removeTask(int index) {
        File file = new File(data.get(index).getFilePath());
        if (file.delete()) {
            data.remove(index);
            return true;
        } else return false;
    }

    public static boolean modifiedTask(int index, Card card) {
        return removeTask(index) && addTask(card);
    }

    public static Card getTask(int cardIndex) {
        return data.get(cardIndex);
    }
}
