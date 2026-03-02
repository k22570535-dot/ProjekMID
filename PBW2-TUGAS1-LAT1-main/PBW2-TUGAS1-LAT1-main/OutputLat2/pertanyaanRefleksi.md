1. Apa keuntungan menggunakan Thymeleaf Fragment untuk navbar dan footer?
   Biar gak kerja dua kali: Kita cukup bikin satu file navbar dan satu file footer, terus tinggal "panggil" di semua halaman lain.
  Gampang update-nya: Kalau besok-besok mau nambah menu di navbar, cukup ubah satu file fragment aja, otomatis semua halaman (Home, About, Products) ikut berubah tanpa perlu kita edit satu-satu.

2. apa bedanya file di `static/` dan `templates/`? Kenapa CSS ada di `static/` bukan `templates/`?
 templates/: Isinya file HTML yang "pintar" karena diproses dulu sama server (pake Thymeleaf) buat masukin data-data sebelum dikirim ke browser.
  static/: Isinya file "polos" kayak CSS, gambar, atau JS yang langsung dikirim ke browser apa adanya tanpa perlu diolah server lagi.
  Kenapa CSS di static/: Karena CSS itu aset statis yang tugasnya cuma buat desain, browser sudah paham isinya tanpa butuh bantuan logika Java.

3. Apa yang dimaksud dengan `th:replace` dan bagaimana bedanya dengan `th:insert`?
    - Hint: coba ganti `th:replace` jadi `th:insert` dan inspect element di browser
  th:replace: Dia bakal ngapus tag tempat kita manggil dan ngganti seluruhnya sama isi fragment-nya.
  th:insert: Dia bakal masukin isi fragment ke dalam tag tempat kita manggil (tag penampungnya masih ada).
  Bedanya: Kalau kita Inspect Element di browser, hasil th:replace itu lebih bersih karena gak ada tag <div> atau <footer> yang numpuk-numpuk.

4. Kenapa kita pakai `@{}` untuk URL di Thymeleaf, bukan langsung tulis path?
   Biar gak "pecah" link-nya: Sintaks @{} itu pintar karena otomatis nambahin Context Path aplikasi. Jadi kalau nanti aplikasi kita di-deploy ke server beneran atau di-hosting di sub-folder, link gambar atau CSS kita bakal tetep ketemu dan gak bakal error 404.
   
5. Perhatikan bahwa `ProductController` inject `ProductService` melalui Constructor Injection (konsep dari Week 3). Apa jadinya kalau Controller tidak pakai DI dan langsung `new ProductService()` di dalam Controller?
   Ribet dan Kaku: Kode jadi susah buat di-tes (unit testing) karena Controller-nya "terikat mati" sama satu objek Service.
   Boros Memori: Kalau ada banyak Controller yang butuh ProductService, tiap Controller bakal bikin objek baru sendiri-sendiri, padahal harusnya cukup satu objek aja yang dipake bareng-bareng (Singleton).
   Data Gak Sinkron: Kalau tiap Controller bikin new Service sendiri, data list produk di memori bisa beda-beda tiap halaman, jadi gak konsisten.
