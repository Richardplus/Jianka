package tech.jianka.data;

/**
 * Created by Richa on 2017/8/8.
 */

public class DataType {
    /**
     * Item 的常量
     */
    public static final int GROUP = 509;
    public static final int CARD = 753;
    public static final int TASK = 666;

    public static final String ACTIVITY_TITLE = "ACTIVITY_TITLE";
    public static final String INIT_TYPE = "INIT_TYPE";
    public static final String CARD_INDEX = "CARD_INDEX";
    public static final String TASK_INDEX = "TASK_INDEX";
    public static final String GROUP_INDEX = "GROUP_INDEX";
    public static final String GROUP_PATH = "GROUP_PATH";
    public static final String GROUP_TYPE = "GROUP_TYPE";

    public static final int NEW_CARD = 152;

    public static final int NEW_TASK = 698;
    public static final int EDIT_CARD = 456;
    public static final int EDIT_TASK = 236;
    public static final int EDIT_DONE = 885;
    public static final int CARD_PLAIN_TEXT = 871;
    /**
     * Task的常量
     */
    public static final int TASK_IMPORTANT_EMERGENT = 627;
    public static final int TASK_IMPORTANT_NOT_EMERGENT = 325;
    public static final int TASK_UNIMPORTANT_EMERGENT = 465;
    public static final int TASK_UNIMPORTANT_NOT_EMERGENT = 789;
    public static final int[] TASK_TYPE = {TASK_IMPORTANT_EMERGENT, TASK_IMPORTANT_NOT_EMERGENT,
            TASK_UNIMPORTANT_EMERGENT, TASK_UNIMPORTANT_NOT_EMERGENT};
    public static final int NEW_GROUP = 846;
    public static final int EDIT_GROUP = 862;
}
