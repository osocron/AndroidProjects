package siscomp.osocron.com.ordencompras.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import com.pixplicity.easyprefs.library.Prefs
import siscomp.osocron.com.ordencompras.MainActivity
import siscomp.osocron.com.ordencompras.R
import siscomp.osocron.com.ordencompras.model.entities.Cliente

class ClientesBaseAdapter(val data: List<Cliente>, ctx: Context) : BaseAdapter(), Filterable {

    private val mInflator: LayoutInflater

    init {
        this.mInflator = LayoutInflater.from(ctx)
    }

    override fun getView(position: Int, converterView: View?, parent: ViewGroup?): View? {
        val view: View?
        val vh: ListRowHolder
        if (converterView == null) {
            view = this.mInflator.inflate(R.layout.cliente_list_item, parent, false)
            vh = ListRowHolder(view)
            view.tag = vh
        } else {
            view = converterView
            vh = view.tag as ListRowHolder
        }

        val cliente: Cliente? = try {
            data.get(position)
        } catch (e: Exception) {
            null
        }

        vh.label.text = try {
            cliente?.nombre
        } catch (e: Exception) {
            ""
        }

        view?.setOnClickListener {
            val intent = Intent(view.context, MainActivity::class.java)
            if (cliente != null && !cliente.clave.isEmpty())
                Prefs.putString("clave_cliente", cliente.clave)
            else
                Prefs.putString("clave_cliente", "not_set")
            view.context.startActivity(intent)
        }

        return view
    }

    override fun getItem(position: Int): Any {
        return data.get(position)
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
            this.label = row?.findViewById(R.id.clienteLVItem) as TextView
        }
    }

}