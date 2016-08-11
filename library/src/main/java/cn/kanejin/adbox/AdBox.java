package cn.kanejin.adbox;

import android.content.Context;
import android.content.res.TypedArray;
import android.database.DataSetObserver;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Kane on 8/3/16.
 */
public class AdBox extends RelativeLayout {
    private static final String TAG = AdBox.class.getSimpleName();

    private boolean isRunning = false;

    private Effect mEffect;
    private int mDuration;
    private int mDelay;
    private boolean mLoop;
    private boolean mAutoPlay;

    private int mPlaceholderResourceId;

    private int mCurrentAdIndex = 0;

    private AdBoxAdapter mAdapter;

    private AdBoxListener mListener;

    public AdBox(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray a = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.AdBox,
                0, 0);

        try {
            mEffect = Effect.valueOfIndex(a.getInteger(R.styleable.AdBox_effect, -1));

            mDuration = a.getInteger(R.styleable.AdBox_duration, 1000);
            mDelay = a.getInteger(R.styleable.AdBox_delay, 5000);
            mLoop = a.getBoolean(R.styleable.AdBox_loop, true);
            mAutoPlay = a.getBoolean(R.styleable.AdBox_auto_play, true);

            mPlaceholderResourceId = a.getResourceId(R.styleable.AdBox_placeholder, R.drawable.adbox_ad_placeholder);

        } finally {
            a.recycle();
        }
    }

    Handler mTimerHandler = new Handler();
    Runnable mTimerRunnable = new Runnable() {

        @Override
        public void run() {
            showNextAd();

            mTimerHandler.postDelayed(this, mDelay);
        }
    };

    public void play() {

        if (!isRunning) {
            mTimerHandler.postDelayed(mTimerRunnable, mDelay);
            isRunning = true;
        }
    }

    public void pause() {
        if (isRunning) {
            mTimerHandler.removeCallbacks(mTimerRunnable);
            isRunning = false;
        }
    }

    public void setListener(AdBoxListener mListener) {
        this.mListener = mListener;
    }

    private DataSetObserver mDataSetObserver;

    public void setAdapter(AdBoxAdapter adapter) {
        if (mAdapter != null && mDataSetObserver != null) {
            mAdapter.unregisterDataSetObserver(mDataSetObserver);
        }

        this.mAdapter = adapter;

        if (mAdapter != null) {

            mDataSetObserver = new DataSetObserver() {
                @Override
                public void onChanged() {
                    rebuildAdBox();
                }
            };
            mAdapter.registerDataSetObserver(mDataSetObserver);
        }

        rebuildAdBox();

        if (mAutoPlay)
            play();
    }

    private void rebuildAdBox() {
        boolean isRunningState = isRunning;
        pause();

        removeAllViewsInLayout();

        for (int i = 0; i < mAdapter.getCount(); i++) {

            final AdItem ad = mAdapter.getItem(i);

            View adView = LayoutInflater.from(getContext()).inflate(R.layout.adbox_ad_view, null);

            ImageView adImage = (ImageView) adView.findViewById(R.id.ad_image);

            Picasso.with(getContext())
                    .load(ad.getImageUrl())
                    .placeholder(mPlaceholderResourceId)
                    .into(adImage);

            final int position = i;
            adView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mListener != null) {
                        mListener.onAdClick(AdBox.this, position, ad);
                    }
                }
            });

            if (i != mCurrentAdIndex) adView.setVisibility(INVISIBLE);
            addView(adView, new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
        }

        requestLayout();
        invalidate();

        if (isRunningState)
            play();
    }

    public void showNextAd() {
        final int nextIndex = nextAdIndex();

        if (nextIndex < 0) {
            pause();
            return ;
        }

        final View currentAd = getChildAt(mCurrentAdIndex);

        AnimationSet set = new AnimationSet(true);
        set.addAnimation(new TranslateAnimation(0f, 0f, 0f, -currentAd.getHeight()));
        set.setDuration(mDuration);
        set.setFillAfter(false);
        currentAd.startAnimation(set);


        final View nextAd = getChildAt(nextIndex);

        AnimationSet set2 = new AnimationSet(true);
        set2.addAnimation(new TranslateAnimation(0f, 0f, nextAd.getHeight(), 0f));
        set2.setDuration(mDuration);
        set2.setFillAfter(true);
        set2.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                mCurrentAdIndex = nextIndex;
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        nextAd.startAnimation(set2);

    }

    private int nextAdIndex() {
        int nextIndex = mCurrentAdIndex + 1;

        if (nextIndex > mAdapter.getCount() - 1)
            return mLoop ? 0 : -1;
        return nextIndex;
    }

    private enum Effect {
        slideLeft(0),
        slideRight(1),
        slideUp(2),
        slideDown(3),
        fade(4);

        private Integer index;
        Effect(Integer index) {
            this.index = index;
        }

        private static final Map<Integer, Effect> ES_MAP = new HashMap<>();
        static {
            for (Effect effect : Effect.values()) {
                ES_MAP.put(effect.index, effect);
            }
        }

        public static Effect valueOfIndex(Integer index) {
            if (index < 0)
                return slideLeft;

            Effect e = ES_MAP.get(index);

            return e == null ? slideLeft : e;
        }
    }
}
