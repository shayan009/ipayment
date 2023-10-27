/*
 * Copyright (C) 2017 Leonid Ustenko Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.onetechsol.ipayment.ui.basefiles.baseadapter;

import android.view.ViewGroup;

import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Base generic RecyclerView adapter.
 * Handles basic logic such as adding/removing items,
 * setting listener, binding ViewHolders.
 * Extend the adapter for appropriate use case.
 *
 * @param <T>  type of objects, which will be used in the adapter's dataset
 * @param <VH> ViewHolder {@link BaseViewHolder}
 * @author Leonid Ustenko (Leo.Droidcoder@gmail.com)
 * @since 1.0.0
 */
public abstract class BaseRecyclerViewAdapter<VB extends ViewDataBinding, T, VH extends BaseViewHolder<VB, T>> extends RecyclerView.Adapter<VH> {

    private List<T> items = new ArrayList<>();

    public BaseRecyclerViewAdapter() {
    }


    public List<T> items() {
        return items;
    }


    /**
     * To be implemented in as specific adapter.
     * Here you should return new ViewHolder instance.
     * You may also return different ViewHolders according to a view type.
     * In this case you shoulf also override {@link RecyclerView.Adapter#getItemViewType(int)}
     *
     * @param parent   The ViewGroup into which the new View will be added after it is bound to an adapter position.
     * @param viewType The view type of the new View.
     * @return A new ViewHolder that holds a View of the given view type.
     */
    @Override
    public abstract VH onCreateViewHolder(ViewGroup parent, int viewType);

    /**
     * Called by RecyclerView to display the data at the specified position. This method should
     * update the contents of the itemView to reflect the item at the given
     * position.
     *
     * @param holder   The ViewHolder which should be updated to represent the contents of the
     *                 item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     */
    @Override
    public void onBindViewHolder(VH holder, int position) {
        if (items != null && items.size() > 0) {
            T item = items.get(position);
            holder.onBind(item);
        }

    }

    /**
     * Returns the total number of items in the data set held by the adapter.
     *
     * @return The total number of items in this adapter.
     */
    @Override
    public int getItemCount() {
        return getItems() != null ? getItems().size() : 0;
    }

    /**
     * Sets items to the adapter and notifies that data set has been changed.
     * Typically this method should be use with `notifyChanges = false` in case you are using DiffUtil
     * {@link DiffUtil} in order to delegate it do all the updating job.
     *
     * @param items         items to set to the adapter
     * @param notifyChanges pass in <code>true</code> to call notifiDatasetChanged {@link RecyclerView.Adapter#notifyDataSetChanged()} or <code>false</code> otherwise
     * @throws IllegalArgumentException in case of setting `null` items
     * @author Leonid Ustenko (Leo.Droidcoder@gmail.com)
     * @since 1.0.0
     */
    public void setItems(List<T> items, boolean notifyChanges) throws IllegalArgumentException {
        if (items == null) {
            throw new IllegalArgumentException("Cannot set `null` item to the Recycler adapter");
        }
        this.items.clear();
        this.items.addAll(items);
        if (notifyChanges) {
            notifyDataSetChanged();
        }
    }

    /**
     * Returns all items from the data set held by the adapter.
     *
     * @return All of items in this adapter.
     * @author Leonid Ustenko (Leo.Droidcoder@gmail.com)
     * @since 1.0.0
     */
    public List<T> getItems() {
        return items;
    }

    /**
     * Sets items to the adapter and notifies that data set has been changed.
     *
     * @param items items to set to the adapter
     * @author Leonid Ustenko (Leo.Droidcoder@gmail.com)
     * @since 1.0.0
     */
    public void setItems(List<T> items) {
        setItems(items, true);
    }

    /**
     * Returns an items from the data set at a certain position.
     *
     * @return All of items in this adapter.
     */
    public T getItem(int position) {
        return items.get(position);
    }

    /**
     * Adds item to the end of the data set.
     * Notifies that item has been inserted.
     *
     * @param item item which has to be added to the adapter.
     * @author Leonid Ustenko (Leo.Droidcoder@gmail.com)
     * @since 1.0.0
     */
    public void add(T item) {
        if (item == null) {
            throw new IllegalArgumentException("Cannot add null item to the Recycler adapter");
        }
        items.add(item);
        notifyItemInserted(items.size() - 1);
    }

    /**
     * Adds item to the beginning of the data set.
     * Notifies that item has been inserted.
     *
     * @param item item which has to be added to the adapter.
     * @author Leonid Ustenko (Leo.Droidcoder@gmail.com)
     * @since 1.0.0
     */
    public void addToBeginning(T item) {
        if (item == null) {
            throw new IllegalArgumentException("Cannot add null item to the Recycler adapter");
        }
        items.add(0, item);
        notifyItemInserted(0);
    }

    /**
     * Adds list of items to the end of the adapter's data set.
     * Notifies that item has been inserted.
     *
     * @param items items which has to be added to the adapter.
     * @author Leonid Ustenko (Leo.Droidcoder@gmail.com)
     * @since 1.0.0
     */
    public void addAll(List<T> items) {
        if (items == null) {
            throw new IllegalArgumentException("Cannot add `null` items to the Recycler adapter");
        }
        this.items.addAll(items);
        notifyItemRangeInserted(this.items.size() - items.size(), items.size());
    }

    /**
     * Clears all the items in the adapter.
     *
     * @author Leonid Ustenko (Leo.Droidcoder@gmail.com)
     * @since 1.0.0
     */
    public void clear() {
        items.clear();
        notifyDataSetChanged();
    }

    /**
     * Removes an item from the adapter.
     * Notifies that item has been removed.
     *
     * @param item to be removed
     * @author Leonid Ustenko (Leo.Droidcoder@gmail.com)
     * @since 1.0.0
     */
    public void remove(T item) {
        int position = items.indexOf(item);
        if (position > -1) {
            items.remove(position);
            notifyItemRemoved(position);
        }
    }

    /**
     * Returns whether adapter is empty or not.
     *
     * @return `true` if adapter is empty or `false` otherwise
     * @author Leonid Ustenko (Leo.Droidcoder@gmail.com)
     * @since 1.0.0
     */
    public boolean isEmpty() {
        return getItemCount() == 0;
    }

    /**
     * Indicates whether each item in the data set can be represented with a unique identifier
     * of type {@link Long}.
     *
     * @param hasStableIds Whether items in data set have unique identifiers or not.
     * @see #hasStableIds()
     * @see #getItemId(int)
     */
    @Override
    public void setHasStableIds(boolean hasStableIds) {
        super.setHasStableIds(hasStableIds);
    }

}
