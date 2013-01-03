package com.wordrecite.activity.word;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;

import com.wordrecite.activity.R;
import com.wordrecite.adapter.word.NewWordListAdapter;
import com.wordrecite.common.MachineMetrics;
import com.wordrecite.entity.Word;
import com.wordrecite.module.word.WordManager;

public class NewWordListActivity extends Activity {
	private int offset = 0;
	private WordManager wordManager;
	private List<Word> wordList;
	private ListView lvWordList;
	private MachineMetrics machineMetrics;
	private int[] colors = { R.color.newword_list_color_first,
			R.color.newword_list_color_second,
			R.color.newword_list_color_third, R.color.newword_list_color_forth,
			R.color.newword_list_color_fifth };

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.newword_list);

		/* ListView初始化 */
		lvWordList = (ListView) findViewById(R.id.lv_newwords);
		wordManager = new WordManager(this);
		wordList = wordManager.select(offset, 5);
		NewWordListAdapter adapter = new NewWordListAdapter(this, wordList,
				colors);
		lvWordList.setAdapter(adapter);
		/*ListView监听事件*/
	    lvWordList.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				Intent intent =new Intent();
				intent.putExtra(wordList.get(arg2).getEnglish(), wordList.get(arg2).getId());
				intent.setClass(NewWordListActivity.this, NewWordDetailActivity.class);
				startActivity(intent);
			}
	    	
		});
		/* 翻页按钮样式 */
		machineMetrics = new MachineMetrics(this);
		TextView prior = (TextView) findViewById(R.id.newword_list_prior);
		TextView next = (TextView) findViewById(R.id.newword_list_next);
		prior.setWidth((machineMetrics.getScreenWidth()) / 2);
		next.setWidth((machineMetrics.getScreenWidth()) / 2);

		/* 上一页 */
		if (offset < 5) {
			prior.setClickable(false);
		} else {
			prior.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					offset -= 5;
					wordList = wordManager.select(offset, 5);
					lvWordList.setAdapter(new NewWordListAdapter(
							NewWordListActivity.this, wordList, colors));
				}
			});
		}

		/* 下一页 */
		if ((offset + 5) >= wordManager.count()) {
			next.setClickable(false);
		} else {
			next.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					offset += 5;
					wordList = wordManager.select(offset, 5);
					lvWordList.setAdapter(new NewWordListAdapter(
							NewWordListActivity.this, wordList, colors));
				}
			});
		}

	}
}
