package jnhouse.topwellsoft.com.jnhouse_android.ui.chat.adapter.ease_adapter;

import android.content.Context;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.TextView.BufferType;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMConversation;
import com.hyphenate.chat.EMGroup;
import com.hyphenate.chat.EMMessage;

import com.hyphenate.util.DateUtils;
import com.topwellsoft.androidutils.PreferencesUtils;

import net.tsz.afinal.FinalHttp;
import net.tsz.afinal.http.AjaxCallBack;
import net.tsz.afinal.http.AjaxParams;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jnhouse.topwellsoft.com.jnhouse_android.R;
import jnhouse.topwellsoft.com.jnhouse_android.constant.JnHouse_Record;
import jnhouse.topwellsoft.com.jnhouse_android.manage.ImageManager;
import jnhouse.topwellsoft.com.jnhouse_android.model.FindUserEntity;
import jnhouse.topwellsoft.com.jnhouse_android.model.LoginEntity;
import jnhouse.topwellsoft.com.jnhouse_android.ui.chat.domain.ease_domain.EaseUser;
import jnhouse.topwellsoft.com.jnhouse_android.ui.chat.domain.ease_domain.ToChatUser;
import jnhouse.topwellsoft.com.jnhouse_android.ui.chat.fragment.chat_fragment.ConversationListFragment;
import jnhouse.topwellsoft.com.jnhouse_android.ui.chat.utils.ease_utils.EaseCommonUtils;
import jnhouse.topwellsoft.com.jnhouse_android.ui.chat.utils.ease_utils.EaseSmileUtils;
import jnhouse.topwellsoft.com.jnhouse_android.ui.chat.utils.ease_utils.EaseUserUtils;
import jnhouse.topwellsoft.com.jnhouse_android.ui.chat.widget.ease_widget.EaseConversationList.EaseConversationListHelper;

/**
 * conversation list adapter
 */
public class EaseConversationAdapater extends ArrayAdapter<EMConversation> {
    private static final String TAG = "ChatAllHistoryAdapter";
    private List<EMConversation> conversationList;
    private List<EMConversation> copyConversationList;
    private ConversationFilter conversationFilter;
    private boolean notiyfyByFilter;

    protected int primaryColor;
    protected int secondaryColor;
    protected int timeColor;
    protected int primarySize;
    protected int secondarySize;
    protected float timeSize;
    private Context context;

    public EaseConversationAdapater(Context context, int resource,
                                    List<EMConversation> objects) {
        super(context, resource, objects);
        this.context = context;
        conversationList = objects;
        copyConversationList = new ArrayList<EMConversation>();
        copyConversationList.addAll(objects);
    }

    @Override
    public int getCount() {
        return conversationList.size();
    }

    @Override
    public EMConversation getItem(int arg0) {
        if (arg0 < conversationList.size()) {
            return conversationList.get(arg0);
        }
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.ease_row_chat_history, parent, false);
        }
        final ViewHolder holder = new ViewHolder();
        holder.name = (TextView) convertView.findViewById(R.id.name);
        holder.unreadLabel = (TextView) convertView.findViewById(R.id.unread_msg_number);
        holder.message = (TextView) convertView.findViewById(R.id.message);
        holder.time = (TextView) convertView.findViewById(R.id.time);
        holder.avatar = (ImageView) convertView.findViewById(R.id.avatar);
        holder.msgState = convertView.findViewById(R.id.msg_state);
        holder.list_itease_layout = (RelativeLayout) convertView.findViewById(R.id.list_itease_layout);
        holder.motioned = (TextView) convertView.findViewById(R.id.mentioned);
        convertView.setTag(holder);
        final int pos = position;
        final EMConversation conversation = getItem(position);
        LoginEntity loginEntity = (LoginEntity) PreferencesUtils.getObject(context, "loginEntity");
        final String username = conversation.getUserName();
        AjaxParams params = new AjaxParams();
        params.put("userUUID", loginEntity.getUserUUID());
        params.put("username", username);
        params.put("logo", "0");
        FinalHttp fh = new FinalHttp();
        fh.get(JnHouse_Record.Find_User, params, new AjaxCallBack<Object>() {

            @Override
            public void onFailure(Throwable t, int errorNo, String strMsg) {
                Log.i("####", "onFailure");
            }

            @Override
            public void onLoading(long count, long current) {
                super.onLoading(count, current);
            }

            @Override
            public void onStart() {
                super.onStart();
            }

            @Override
            public void onSuccess(Object t) {
                try {
                    Log.i("####", t.toString());
                    Gson gson = new Gson();
                    FindUserEntity findUserEntity = gson.fromJson(t.toString(), new TypeToken<FindUserEntity>() {
                    }.getType());
                    ToChatUser user = new ToChatUser();
                    user.setAvatar(findUserEntity.getAvatar());
                    user.setNickname(findUserEntity.getRealname());
                    PreferencesUtils.putObject(context, username, user);
                    holder.list_itease_layout.setBackgroundResource(R.drawable.ease_mm_listitem);
                    new ImageManager(context).loadCircleImage(user.getAvatar(), holder.avatar);
                    holder.name.setText(user.getNickname());
                    holder.motioned.setVisibility(View.GONE);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (pos == conversationList.size()) {
                        if (ConversationListFragment.diaLog.isShowing())
                            ConversationListFragment.diaLog.dismiss();
                    }
                }
            }
        });
        if (conversation.getUnreadMsgCount() > 0) {
            holder.unreadLabel.setText(String.valueOf(conversation.getUnreadMsgCount()));
            holder.unreadLabel.setVisibility(View.VISIBLE);
        } else {
            holder.unreadLabel.setVisibility(View.INVISIBLE);
        }

        if (conversation.getAllMsgCount() != 0) {
            EMMessage lastMessage = conversation.getLastMessage();
            String content = null;
            if (cvsListHelper != null) {
                content = cvsListHelper.onSetItemSecondaryText(lastMessage);
            }
            holder.message.setText(EaseSmileUtils.getSmiledText(getContext(), EaseCommonUtils.getMessageDigest(lastMessage, (this.getContext()))), BufferType.SPANNABLE);
            if (content != null) {
                holder.message.setText(content);
            }
            holder.time.setText(DateUtils.getTimestampString(new Date(lastMessage.getMsgTime())));
            if (lastMessage.direct() == EMMessage.Direct.SEND && lastMessage.status() == EMMessage.Status.FAIL) {
                holder.msgState.setVisibility(View.VISIBLE);
            } else {
                holder.msgState.setVisibility(View.GONE);
            }
        }

        //set property
        holder.name.setTextColor(primaryColor);
        holder.message.setTextColor(secondaryColor);
        holder.time.setTextColor(timeColor);
        if (primarySize != 0)
            holder.name.setTextSize(TypedValue.COMPLEX_UNIT_PX, primarySize);
        if (secondarySize != 0)
            holder.message.setTextSize(TypedValue.COMPLEX_UNIT_PX, secondarySize);
        if (timeSize != 0)
            holder.time.setTextSize(TypedValue.COMPLEX_UNIT_PX, timeSize);
        return convertView;
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
        if (!notiyfyByFilter) {
            copyConversationList.clear();
            copyConversationList.addAll(conversationList);
            notiyfyByFilter = false;
        }
    }

    @Override
    public Filter getFilter() {
        if (conversationFilter == null) {
            conversationFilter = new ConversationFilter(conversationList);
        }
        return conversationFilter;
    }


    public void setPrimaryColor(int primaryColor) {
        this.primaryColor = primaryColor;
    }

    public void setSecondaryColor(int secondaryColor) {
        this.secondaryColor = secondaryColor;
    }

    public void setTimeColor(int timeColor) {
        this.timeColor = timeColor;
    }

    public void setPrimarySize(int primarySize) {
        this.primarySize = primarySize;
    }

    public void setSecondarySize(int secondarySize) {
        this.secondarySize = secondarySize;
    }

    public void setTimeSize(float timeSize) {
        this.timeSize = timeSize;
    }


    private class ConversationFilter extends Filter {
        List<EMConversation> mOriginalValues = null;

        public ConversationFilter(List<EMConversation> mList) {
            mOriginalValues = mList;
        }

        @Override
        protected FilterResults performFiltering(CharSequence prefix) {
            FilterResults results = new FilterResults();
            if (mOriginalValues == null) {
                mOriginalValues = new ArrayList<EMConversation>();
            }
            if (prefix == null || prefix.length() == 0) {
                results.values = copyConversationList;
                results.count = copyConversationList.size();
            } else {
                String prefixString = prefix.toString();
                final int count = mOriginalValues.size();
                final ArrayList<EMConversation> newValues = new ArrayList<EMConversation>();

                for (int i = 0; i < count; i++) {
                    final EMConversation value = mOriginalValues.get(i);
                    String username = value.getUserName();

                    EMGroup group = EMClient.getInstance().groupManager().getGroup(username);
                    if (group != null) {
                        username = group.getGroupName();
                    } else {
                        EaseUser user = EaseUserUtils.getUserInfo(username);
                    }
                    if (username.startsWith(prefixString)) {
                        newValues.add(value);
                    } else {
                        final String[] words = username.split(" ");
                        final int wordCount = words.length;
                        for (int k = 0; k < wordCount; k++) {
                            if (words[k].startsWith(prefixString)) {
                                newValues.add(value);
                                break;
                            }
                        }
                    }
                }
                results.values = newValues;
                results.count = newValues.size();
            }
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            conversationList.clear();
            if (results.values != null) {
                conversationList.addAll((List<EMConversation>) results.values);
            }
            if (results.count > 0) {
                notiyfyByFilter = true;
                notifyDataSetChanged();
            } else {
                notifyDataSetInvalidated();
            }
        }
    }

    private EaseConversationListHelper cvsListHelper;

    public void setCvsListHelper(EaseConversationListHelper cvsListHelper) {
        this.cvsListHelper = cvsListHelper;
    }

    private static class ViewHolder {
        TextView name;
        TextView unreadLabel;
        TextView message;
        TextView time;
        ImageView avatar;
        View msgState;
        RelativeLayout list_itease_layout;
        TextView motioned;
    }
}

