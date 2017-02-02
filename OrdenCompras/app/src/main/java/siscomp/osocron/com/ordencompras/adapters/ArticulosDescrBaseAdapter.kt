package siscomp.osocron.com.ordencompras.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import siscomp.osocron.com.ordencompras.R
import siscomp.osocron.com.ordencompras.asyncTasks.articulos.SearchArticulosInfoTask
import siscomp.osocron.com.ordencompras.model.db.OrdenComprasDbHelper
import siscomp.osocron.com.ordencompras.model.json.JsonArticuloDescr


class ArticulosDescrBaseAdapter(val data: List<JsonArticuloDescr>,
                                val ctx: Context) : BaseAdapter(), Filterable {

    private val mInflator: LayoutInflater

    init {
        this.mInflator = LayoutInflater.from(ctx)
    }

    override fun getView(position: Int, converterView: View?, parent: ViewGroup?): View? {
        val view: View?
        val vh: ArticulosDescrBaseAdapter.ListRowHolder
        if (converterView == null) {
            view = this.mInflator.inflate(R.layout.articulo_descr_list_item, parent, false)
            vh = ArticulosDescrBaseAdapter.ListRowHolder(view)
            view.tag = vh
        } else {
            view = converterView
            vh = view.tag as ArticulosDescrBaseAdapter.ListRowHolder
        }

        val jsonArticulo: JsonArticuloDescr? = try {
            data.get(position)
        } catch (e: Exception) {
            null
        }

        vh.label.text = try {
            jsonArticulo?.descripcion
        } catch (e: Exception) {
            ""
        }

        view?.setOnClickListener {
            SearchArticulosInfoTask(view.context, OrdenComprasDbHelper(view.context), jsonArticulo?.clave).execute()
        }

        return view
    }

    override fun getItem(position: Int): Any {
        return data[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return data.size
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(p0: CharSequence?): FilterResults {
                return FilterResults()
            }
            override fun publishResults(p0: CharSequence?, p1: FilterResults?) { }
        }
    }

    private class ListRowHolder(row: View?) {
        val label: TextView
        init {
            this.label = row?.findViewById(R.id.articuloDescrLVItem) as TextView
        }
    }

}