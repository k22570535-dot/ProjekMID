1. Apa perbedaan antara @Controller dan @RestController? Dalam kasus apa kamu pakai masing-masing?
   Perbedaan: @Controller itu dipake kalau kita mau nampilin halaman (HTML/Thymeleaf). Jadi dia bakal nyari file di folder templates. Sedangkan @RestController itu isinya @Controller + @ResponseBody, jadi dia langsung balikin data (biasanya JSON atau teks) tanpa nyari file HTML.
kapan dipake
@Controller: Pas kita mau bikin web yang ada tampilannya (pake Bootstrap, CSS, dll).
@RestController: Pas kita mau bikin API buat aplikasi mobile atau kalau kita mau kirim data mentah aja.

2. Perhatikan bahwa template product/list.html dipakai oleh 3 endpoint berbeda (list all, filter by category, search). Apa keuntungannya membuat template yang reusable seperti ini?
   Keuntungan: Hemat tenaga dan kode jadi lebih rapi Kita nggak perlu bikin 3 file HTML yang isinya mirip-mirip. Kalau nanti mau ganti desain tabel atau warna tombol, cukup ubah di satu file list.html aja, maka fitur list all, filter, dan search otomatis keganti semua.

3. Kenapa Controller inject ProductService (bukan langsung akses data di ArrayList)? Apa yang terjadi kalau Controller langsung manage data?
   Kenapa: Biar rapi dan terbagi tugasnya ). Controller tugasnya cuma ngatur traffic (request/response), sedangkan urusan olah data itu tugasnya Service.

   Kalau Manage Langsung: Kodenya bakal berantakan . Kalau ada 5 Controller yang butuh data produk, kita harus nulis ulang logika ambil datanya di 5 tempat. Kalau pake Service, tinggal panggil satu tempat yang sama.

4. Apa perbedaan model.addAttribute("products", products) dengan return products langsung seperti di @RestController?
   model.addAttribute: Kita kayak "nitip" data ke dalam kantong (Model) supaya bisa diambil dan dipajang sama Thymeleaf di file HTML.
   return langsung: Data itu langsung dilempar ke browser sebagai teks atau JSON. Browser nggak bakal nampilin desain apa-apa, cuma tulisan mentah doang.

5. Jika kamu buka http://localhost:8080/products/abc (ID bukan angka), apa yang terjadi? Kenapa?
   Apa yang terjadi: Bakal muncul error 400 Bad Request atau Method Argument Type Mismatch.
   Kenapa: Karena di Controller kita minta parameter Long id, tapi kita malah ngasih "abc" (String). Spring nggak bisa paksa "abc" jadi angka, makanya dia protes karena tipenya nggak cocok.

6. Apa keuntungan pakai @RequestMapping("/products") di level class dibanding menulis full path di setiap @GetMapping?
   Keuntungan: Biar nggak pegel ngetik! Kita nggak perlu nulis /products/list, /products/add, /products/delete satu-satu. Kita cukup tentukan "base path"-nya di atas, jadi di setiap method tinggal tulis sisanya aja. Selain itu, kalau suatu saat mau ganti URL jadi /barang, kita cuma perlu ubah di satu tempat di level class.

7. Dalam lab ini, kata "Model" muncul dalam beberapa konteks berbeda. Sebutkan minimal 2 arti yang berbeda dan jelaskan perbedaannya.
      Model (Folder/Package model/ & Class Product): Ini artinya Data Object. Ini adalah representasi data di dunia nyata (seperti Produk, User, Kategori) yang kita simpan di aplikasi.

   Model (Parameter di Method Controller): Ini adalah Container/Wadah. Ini adalah objek bawaan Spring yang fungsinya buat nampung data dari Controller supaya bisa "nyebrang" dan dibaca oleh Thymeleaf di sisi View.
   
