/*
 * Copyright 2018-present KunMinX
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.laozhang.project.main.adapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

import com.laozhang.project.main.model.CommentModel;
import com.laozhang.project.main.model.ConsumerModel;
import com.laozhang.project.main.model.RecommendModel;

/**
 * Create by KunMinX at 2020/7/19
 */
public class DiffUtils {


    private DiffUtil.ItemCallback<ConsumerModel> mTestMusicItemCallback;
    private DiffUtil.ItemCallback<RecommendModel> mTestRecommendItemCallback;
    private DiffUtil.ItemCallback<String> mTestStringItemCallback;
    private DiffUtil.ItemCallback<CommentModel> mCommentModelItemCallback;

    private DiffUtils() {
    }

    private static final DiffUtils S_DIFF_UTILS = new DiffUtils();

    public static DiffUtils getInstance() {
        return S_DIFF_UTILS;
    }



    public DiffUtil.ItemCallback<ConsumerModel> getConsumerItemCallback() {
        if (mTestMusicItemCallback == null) {
            mTestMusicItemCallback = new DiffUtil.ItemCallback<ConsumerModel>() {
                @Override
                public boolean areItemsTheSame(@NonNull ConsumerModel oldItem, @NonNull ConsumerModel newItem) {
                    return oldItem.equals(newItem);
                }

                @Override
                public boolean areContentsTheSame(@NonNull ConsumerModel oldItem, @NonNull ConsumerModel newItem) {
                    return oldItem.userId == newItem.userId;
                }
            };
        }
        return mTestMusicItemCallback;
    }
    public DiffUtil.ItemCallback<RecommendModel> getRecommendItemCallback() {
        if (mTestRecommendItemCallback == null) {
            mTestRecommendItemCallback = new DiffUtil.ItemCallback<RecommendModel>() {
                @Override
                public boolean areItemsTheSame(@NonNull RecommendModel oldItem, @NonNull RecommendModel newItem) {
                    return oldItem.equals(newItem);
                }

                @Override
                public boolean areContentsTheSame(@NonNull RecommendModel oldItem, @NonNull RecommendModel newItem) {
                    return oldItem.user.userId == newItem.user.userId;
                }
            };
        }
        return mTestRecommendItemCallback;
    }
    public DiffUtil.ItemCallback<String> getStringItemCallback() {
        if (mTestStringItemCallback == null) {
            mTestStringItemCallback = new DiffUtil.ItemCallback<String>() {
                @Override
                public boolean areItemsTheSame(@NonNull String oldItem, @NonNull String newItem) {
                    return oldItem.equals(newItem);
                }

                @Override
                public boolean areContentsTheSame(@NonNull String oldItem, @NonNull String newItem) {
                    return oldItem.equals(newItem);
                }
            };
        }
        return mTestStringItemCallback;
    }
    public DiffUtil.ItemCallback<CommentModel> getCommentModelItemCallback() {
        if (mCommentModelItemCallback == null) {
            mCommentModelItemCallback = new DiffUtil.ItemCallback<CommentModel>() {
                @Override
                public boolean areItemsTheSame(@NonNull CommentModel oldItem, @NonNull CommentModel newItem) {
                    return oldItem.equals(newItem);
                }

                @Override
                public boolean areContentsTheSame(@NonNull CommentModel oldItem, @NonNull CommentModel newItem) {
                    return oldItem.commentId == newItem.commentId;
                }
            };
        }
        return mCommentModelItemCallback;
    }

}
