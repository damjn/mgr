$(document).ready(function() {
//    var titleValidators = {
//            row: '.col-xs-4',   // The title is placed inside a <div class="col-xs-4"> element
//            validators: {
//                notEmpty: {
//                    message: 'The title is required'
//                }
//            }
//        },
//        isbnValidators = {
//            row: '.col-xs-4',
//            validators: {
//                notEmpty: {
//                    message: 'The ISBN is required'
//                },
//                isbn: {
//                    message: 'The ISBN is not valid'
//                }
//            }
//        },
//        priceValidators = {
//            row: '.col-xs-2',
//            validators: {
//                notEmpty: {
//                    message: 'The price is required'
//                },
//                numeric: {
//                    message: 'The price must be a numeric number'
//                }
//            }
//        },
//        bookIndex = 0;

    $('#courseForm')
//        .formValidation({
//            framework: 'bootstrap',
//            icon: {
//                valid: 'glyphicon glyphicon-ok',
//                invalid: 'glyphicon glyphicon-remove',
//                validating: 'glyphicon glyphicon-refresh'
//            },
//            fields: {
//                'book[0].title': titleValidators,
//                'book[0].isbn': isbnValidators,
//                'book[0].price': priceValidators
//            }
//        })

        // Add button click handler
        .on('click', '.addButton', function() {
            contentIndex++;
            var $template = $('#contentTemplate'),
                $clone    = $template
                                .clone()
                                .removeClass('hide')
                                .removeAttr('id')
                                .attr('data-content-index', contentIndex)
                                .insertBefore($template);

            // Update the name attributes
            $clone
                .find('[name="title"]').attr('th:field', '*{trainingDTO.contentDTO[' + contentIndex + '].order').end()
                .find('[name="isbn"]').attr('th:field', '*{trainingDTO.contentDTO[' + contentIndex + '].file').end()
                .find('[name="price"]').attr('th:field', '*{trainingDTO.contentDTO[' + contentIndex + '].description').end();

            // Add new fields
            // Note that we also pass the validator rules for new field as the third parameter
//            $('#bookForm')
//                .formValidation('addField', 'book[' + bookIndex + '].title', titleValidators)
//                .formValidation('addField', 'book[' + bookIndex + '].isbn', isbnValidators)
//                .formValidation('addField', 'book[' + bookIndex + '].price', priceValidators);
        })

        // Remove button click handler
        .on('click', '.removeButton', function() {
            var $row  = $(this).parents('.form-group'),
                index = $row.attr('data-content-index');

            // Remove fields
            $('#bookForm')
                .formValidation('removeField', $row.find('[th:field="*{trainingDTO.contentDTO[' + index + '].order"]'))
                .formValidation('removeField', $row.find('[th:field="*{trainingDTO.contentDTO[' + index + '].file"]'))
                .formValidation('removeField', $row.find('[th:field="*{trainingDTO.contentDTO[' + index + '].description"]'));

            // Remove element containing the fields
            $row.remove();
        });
});