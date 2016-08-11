package cn.kanejin.adbox.log;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Kane on 8/10/16.
 */
class ViewLogUtil {

    static String debugLayoutParams(ViewGroup.LayoutParams params) {
        if (params == null) {
            return "BAD! no layout params";
        }

        return "LayoutParams={type=" + params.getClass().getName() +", width="
                + sizeToString(params.width) + ", height=" + sizeToString(params.height) + " }";
    }

    static String sizeToString(int size) {
        if (size == ViewGroup.LayoutParams.WRAP_CONTENT) {
            return "wrap-content";
        }
        if (size == ViewGroup.LayoutParams.MATCH_PARENT) {
            return "match-parent";
        }
        return String.valueOf(size);
    }

    static String debugIndent(int depth) {
        StringBuilder spaces = new StringBuilder((depth * 2 + 3) * 2);
        for (int i = 0; i < (depth * 2) + 3; i++) {
            spaces.append(' ').append(' ');
        }
        return "\n" + spaces.toString();
    }

    static String debugView(View view, int depth) {
        String output = generateViewDebugInfo(view, depth);

        if (view instanceof ViewGroup) {

            ViewGroup viewGroup = (ViewGroup) view;

            if (viewGroup.getFocusedChild() != null) {
                output += debugIndent(depth);
                output += "mFocused";
            }

            if (viewGroup.getChildCount() <= 0) {
                return output;
            }

            output += debugIndent(depth);
            output += "{";

            for (int i = 0; i < viewGroup.getChildCount(); i++) {
                output += debugView(viewGroup.getChildAt(i), depth + 1);
            }
            output += debugIndent(depth);
            output += "}";

        }

        return output;
    }

    static String generateViewDebugInfo(View view, int depth) {

        String output = debugIndent(depth - 1);

        output += "+ " + view;
        int id = view.getId();
        if (id != -1) {
            output += " (id=" + id + ")";
        }
        Object tag = view.getTag();
        if (tag != null) {
            output += " (tag=" + tag + ")";
        }

        if (view.hasFocus()) {
            output += debugIndent(depth) + " FOCUSED";
        }

        output += debugIndent(depth);
        output += "frame={" + view.getLeft() + ", " + view.getTop() + ", " + view.getRight()
                + ", " + view.getBottom() + "} scroll={" + view.getScrollX() + ", " + view.getScrollY()
                + "}";

        if (view.getPaddingLeft() != 0
                || view.getPaddingTop() != 0
                || view.getPaddingRight() != 0
                || view.getPaddingBottom() != 0) {
            output += debugIndent(depth);
            output += "padding={" + view.getPaddingLeft() + ", " + view.getPaddingTop()
                    + ", " + view.getPaddingRight() + ", " + view.getPaddingBottom() + "}";
        }

        output += debugIndent(depth);
        output += "mMeasureWidth=" + view.getMeasuredWidthAndState() +
                " mMeasureHeight=" + view.getMeasuredHeightAndState();

        output += debugIndent(depth);
        output += debugLayoutParams(view.getLayoutParams());

        output += debugIndent(depth);
        output += "flags={";
        output += printFlags(view);
        output += "}";

        return output;
    }

    static String printFlags(View view) {
        String output = "";
        int numFlags = 0;

        if (view.hasFocus()) {
            output += "TAKES_FOCUS";
            numFlags++;
        }

        switch (view.getVisibility()) {
            case View.INVISIBLE:
                if (numFlags > 0) {
                    output += " ";
                }
                output += "INVISIBLE";
                // USELESS HERE numFlags++;
                break;
            case View.GONE:
                if (numFlags > 0) {
                    output += " ";
                }
                output += "GONE";
                // USELESS HERE numFlags++;
                break;
            default:
                break;
        }
        return output;
    }
}
