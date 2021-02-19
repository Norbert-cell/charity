document.addEventListener("DOMContentLoaded", function() {

  /**
   * Form Select
   */
  class FormSelect {
    constructor($el) {
      this.$el = $el;
      this.options = [...$el.children];
      this.init();
    }

    init() {
      this.createElements();
      this.addEvents();
      this.$el.parentElement.removeChild(this.$el);
    }

    createElements() {
      // Input for value
      this.valueInput = document.createElement("input");
      this.valueInput.type = "text";
      this.valueInput.name = this.$el.name;

      // Dropdown container
      this.dropdown = document.createElement("div");
      this.dropdown.classList.add("dropdown");

      // List container
      this.ul = document.createElement("ul");

      // All list options
      this.options.forEach((el, i) => {
        const li = document.createElement("li");
        li.dataset.value = el.value;
        li.innerText = el.innerText;

        if (i === 0) {
          // First clickable option
          this.current = document.createElement("div");
          this.current.innerText = el.innerText;
          this.dropdown.appendChild(this.current);
          this.valueInput.value = el.value;
          li.classList.add("selected");
        }

        this.ul.appendChild(li);
      });

      this.dropdown.appendChild(this.ul);
      this.dropdown.appendChild(this.valueInput);
      this.$el.parentElement.appendChild(this.dropdown);
    }

    addEvents() {
      this.dropdown.addEventListener("click", e => {
        const target = e.target;
        this.dropdown.classList.toggle("selecting");

        // Save new value only when clicked on li
        if (target.tagName === "LI") {
          this.valueInput.value = target.dataset.value;
          this.current.innerText = target.innerText;
        }
      });
    }
  }
  document.querySelectorAll(".form-group--dropdown select").forEach(el => {
    new FormSelect(el);
  });

  /**
   * Hide elements when clicked on document
   */
  document.addEventListener("click", function(e) {
    const target = e.target;
    const tagName = target.tagName;

    if (target.classList.contains("dropdown")) return false;

    if (tagName === "LI" && target.parentElement.parentElement.classList.contains("dropdown")) {
      return false;
    }

    if (tagName === "DIV" && target.parentElement.classList.contains("dropdown")) {
      return false;
    }

    document.querySelectorAll(".form-group--dropdown .dropdown").forEach(el => {
      el.classList.remove("selecting");
    });
  });

  /**
   * Switching between form steps
   */
  class FormSteps {
    constructor(form) {
      this.$form = form;
      this.$next = form.querySelectorAll(".next-step");
      this.$prev = form.querySelectorAll(".prev-step");
      this.$step = form.querySelector(".form--steps-counter span");
      this.currentStep = 1;

      this.$stepInstructions = form.querySelectorAll(".form--steps-instructions p");
      const $stepForms = form.querySelectorAll("form > div");
      this.slides = [...this.$stepInstructions, ...$stepForms];

      this.init();
    }

    /**
     * Init all methods
     */
    init() {
      this.events();
      this.updateForm();
    }

    /**
     * All events that are happening in form
     */
    events() {
      // Next step
      this.$next.forEach(btn => {
        btn.addEventListener("click", e => {
          e.preventDefault();
          this.currentStep++;
          this.updateForm();
        });
      });

      // Previous step
      this.$prev.forEach(btn => {
        btn.addEventListener("click", e => {
          e.preventDefault();
          this.currentStep--;
          this.updateForm();
        });
      });

      // Form submit
      this.$form.querySelector("form").addEventListener("submit", e => this.submit(e));
    }

    /**
     * Update form front-end
     * Show next or previous section etc.
     */
    updateForm() {
      this.$step.innerText = this.currentStep;

      // TODO: Validation


      this.slides.forEach(slide => {
        slide.classList.remove("active");

        if (slide.dataset.step == this.currentStep) {
          slide.classList.add("active");
        }
      });

      this.$stepInstructions[0].parentElement.parentElement.hidden = this.currentStep >= 5;
      this.$step.parentElement.hidden = this.currentStep >= 5;

      // TODO: get data from inputs and show them in summary

      if (true) {

        const summaryQuantityAndCategory = document.getElementById("quantityAndCategory");

        const summaryFoundation = document.getElementById("foundation");

        const summaryStreet = document.getElementById("street");

        const summaryCity = document.getElementById("summaryCity");

        const summaryZipCode = document.getElementById("zip");

        const summaryDate = document.getElementById("date");

        const summaryTime = document.getElementById("time");

        const summaryComment = document.getElementById("comment");


        const chooseQuantity = document.getElementById("bags").value;

        const categories = [];

        const chooseCategory = document.querySelectorAll("#category");
        chooseCategory.forEach(function (element) {
          if (element.checked) {
            const text = element.parentElement.querySelector(".description").innerText;
            categories.push(text);
          }
        })

        let institution = null;

        const chooseInstitution = document.querySelectorAll("#institution");
        chooseInstitution.forEach(function (element) {
          if (element.checked){
           const text= element.parentElement.querySelector(".description").innerText;
           institution = text;
          }

        })

        const chooseAddress = document.getElementById("address").value;

        const chooseCity = document.getElementById("city").value;

        const choosePostCode = document.getElementById("postcode").value;

        const choosePickUpDate = document.getElementById("pickUpDate").value;

        const choosePickUpTime = document.getElementById("pickUpTime").value;

        const choosePickUpComment = document.getElementById("pickUpComment").value;


        summaryQuantityAndCategory.innerText=chooseQuantity +' worki ' + categories;

        summaryFoundation.innerText = institution;

        summaryStreet.innerText = chooseAddress;

        summaryCity.innerText = chooseCity;

        summaryZipCode.innerText = choosePostCode;

        summaryDate.innerText = choosePickUpDate;

        summaryTime.innerText = choosePickUpTime;

        summaryComment.innerText = choosePickUpComment;

      }



    }

  }
  const form = document.querySelector(".form--steps");
  if (form !== null) {
    new FormSteps(form);
  }

})
